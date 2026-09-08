package com.devsuperior.dslist.core.usecase.picpay_challenge.input;

import com.devsuperior.dslist.core.domain.picpay_challenge.TypeUserPicPay;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UserPicPayInput(@JsonProperty("nome") String firstName,
                              @JsonProperty("sobrenome") String lastName,
                              @JsonProperty("documento") String document,
                              @JsonProperty("email") String email,
                              @JsonProperty("senha") String password,
                              @JsonProperty("saldo") BigDecimal balance,
                              @JsonProperty("tipo") TypeUserPicPay userType) {
}
