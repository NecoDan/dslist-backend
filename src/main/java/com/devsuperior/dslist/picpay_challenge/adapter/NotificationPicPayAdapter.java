package com.devsuperior.dslist.picpay_challenge.adapter;

import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.dto.external.NotificationDTO;
import com.devsuperior.dslist.picpay_challenge.ports.NotificationPicPayPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationPicPayAdapter implements NotificationPicPayPort {

    private static final String PATH_TRANSACTION_NOTIFICATION = "/v1/notify";

    private static final String PATH_TRANSACTION_NOTIFICATION_MOCK = URI.create("https://run.mocky.io/v3/6b650a60-a8de-43f3-a5b9-290f43feca33")
            .toString();

    @Value("${address.service.base.url.apis.picpay}") private String uriApiPicPay;

    private final RestTemplate restTemplate;

    @Override
    public void sendNotification(User user, String message) {

        final var email = user.getEmail();
        final var successMessage = String.format("Serviço de notificação para transação API PicPay " +
                "enviado com sucesso %s - %s", email, message);

        final var notificationRequest = new NotificationDTO(email, message);
        final var uri = URI.create(PATH_TRANSACTION_NOTIFICATION_MOCK);

        var headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("correlationId", UUID.randomUUID().toString());
        headers.add("flowId", UUID.randomUUID().toString());

        final HttpEntity<NotificationDTO> httpEntity = new HttpEntity<>(notificationRequest, headers);
        final ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
        // final ResponseEntity<String> response = this.restTemplate.postForEntity(uri, notificationRequest, String.class);

        if (response.getStatusCode() != HttpStatus.CREATED) {
            final var errorMessage = "Serviço de notificação para transação API PicPay encontra-se fora do ar!";
            log.error(errorMessage);
            throw new InvalidDataAccessApiUsageException(errorMessage);
        }

        log.info(successMessage);
    }
}
