package com.devsuperior.dslist.picpay_challenge.ports;

import com.devsuperior.dslist.picpay_challenge.dto.external.AuthorizationDTO;

import java.util.Optional;

public interface AuthorizationPicPayPort {
    Optional<AuthorizationDTO> getAuthorizationTransaction();

    Optional<AuthorizationDTO> getAuthorizationTransactionBy();
}
