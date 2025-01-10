package com.devsuperior.dslist.picpay_challenge.adapter;

import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.dto.external.NotificationDTO;
import com.devsuperior.dslist.picpay_challenge.ports.NotificationPicPayPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationPicPayAdapter implements NotificationPicPayPort {

    private static final String PATH_TRANSACTION_NOTIFICATION = "/v1/notify";

    @Value("${address.service.base.url.apis.picpay}")
    private String uriApiPicPay;

    private final RestTemplate restTemplate;

    @Override
    public void sendNotification(User user, String message){
        final var email = user.getEmail();
        final var notificationRequest = new NotificationDTO(email, message);

        final var uri = URI.create(uriApiPicPay.concat(PATH_TRANSACTION_NOTIFICATION));
        ResponseEntity<String> response = this.restTemplate.postForEntity(uri, notificationRequest, String.class);

        if (response.getStatusCode() != HttpStatus.OK){
            final var errorMessage = "Serviço de notificação para transação API PicPay encontra-se fora do ar!";
            log.error(errorMessage);

            throw new InvalidDataAccessApiUsageException(errorMessage);
        }
    }
}
