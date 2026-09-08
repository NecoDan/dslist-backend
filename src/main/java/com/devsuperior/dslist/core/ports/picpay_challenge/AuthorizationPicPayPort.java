package com.devsuperior.dslist.core.ports.picpay_challenge;

import com.devsuperior.dslist.adapter.out.dto.external.picpay.AuthorizationDTO;

import java.util.Optional;

public interface AuthorizationPicPayPort {

    Optional<AuthorizationDTO> getAuthorizationTransaction();

    Optional<AuthorizationDTO> getAuthorizationTransactionBy();
}
