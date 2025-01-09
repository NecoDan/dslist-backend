package com.devsuperior.dslist.picpay_challenge.adapter;


import com.devsuperior.dslist.picpay_challenge.dto.external.AuthorizationDTO;
import com.devsuperior.dslist.picpay_challenge.dto.external.DataAuthorizationDTO;
import com.devsuperior.dslist.picpay_challenge.ports.AuthorizationPicPayPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthorizationPicPayAdapter implements AuthorizationPicPayPort {

    private static final String PATH_TRANSACTION_AUTHORIZATION = "/v2/authorize";

    @Value("${address.service.base.url.apis.picpay}")
    private String uriApiPicPay;

    private final RestTemplate restTemplate;

    @Override
    public Optional<AuthorizationDTO> getAuthorizationTransaction() throws Exception {
        final String url = URI.create(
                uriApiPicPay.concat(PATH_TRANSACTION_AUTHORIZATION)
        ).getPath();

        ResponseEntity<DataAuthorizationDTO> response = restTemplate.getForEntity(url, DataAuthorizationDTO.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Falha ao obter dados autorização da transação API PicPay.");
            throw new Exception("Falha ao obter dados autorização da transação API PicPay.");
        }

        return Optional.of(Objects.requireNonNull(response.getBody()).getData());
    }

}
