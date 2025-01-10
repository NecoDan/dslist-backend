package com.devsuperior.dslist.picpay_challenge.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequestDTO implements Serializable {

    @JsonProperty("valor")
    private BigDecimal value;

    @JsonProperty("idUsuarioPagador")
    private Long senderId;

    @JsonProperty("idUsuarioRecebedor")
    private Long receiverId;
}
