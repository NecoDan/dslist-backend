package com.devsuperior.dslist.core.usecase.picpay_challenge.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionPicPayInput(@JsonProperty("valor") BigDecimal value,
                                     @JsonProperty("idUsuarioPagador") Long senderId,
                                     @JsonProperty("idUsuarioRecebedor") Long receiverId
) {
}
