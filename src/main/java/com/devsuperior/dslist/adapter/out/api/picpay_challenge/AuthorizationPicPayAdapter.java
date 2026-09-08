package com.devsuperior.dslist.adapter.out.api.picpay_challenge;


import com.devsuperior.dslist.adapter.out.dto.external.picpay.AuthorizationDTO;
import com.devsuperior.dslist.adapter.out.dto.external.picpay.DataAuthorizationDTO;
import com.devsuperior.dslist.core.ports.picpay_challenge.AuthorizationPicPayPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public Optional<AuthorizationDTO> getAuthorizationTransaction() {

        var uri = uriApiPicPay + PATH_TRANSACTION_AUTHORIZATION;
        var url = URI.create(uri);
        ResponseEntity<DataAuthorizationDTO> response = restTemplate.getForEntity(url, DataAuthorizationDTO.class);

        if (response.getStatusCode() != HttpStatus.OK){
            throw validate(response.getStatusCode());
        }

        return Optional.of(Objects.requireNonNull(response.getBody()).getData());
    }

    @Override
    public Optional<AuthorizationDTO> getAuthorizationTransactionBy() {

        var uri = URI.create(uriApiPicPay.concat(PATH_TRANSACTION_AUTHORIZATION));
        ResponseEntity<DataAuthorizationDTO> response = restTemplate.getForEntity(uri, DataAuthorizationDTO.class);

        if (response.getStatusCode() != HttpStatus.OK)
            throw validate(response.getStatusCode());

        return Optional.of(Objects.requireNonNull(response.getBody()).getData());
    }

    private InvalidDataAccessApiUsageException validate(HttpStatusCode httpStatus) {

        final var errorMessage = String.format("Falha ao obter dados autorização da transação API PicPay - [%s].", httpStatus.toString());
        log.error(errorMessage);

        throw new InvalidDataAccessApiUsageException(errorMessage);
    }
}
