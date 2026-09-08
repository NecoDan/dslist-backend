package com.devsuperior.dslist.core.usecase.picpay_challenge.output;

import com.devsuperior.dslist.core.domain.picpay_challenge.TransactionPicPay;
import com.devsuperior.dslist.utils.FunctionalUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
public record TransactionPicPayOutput(@JsonProperty("codigo") Long id,
                                      @JsonProperty("valor") String amount,
                                      @JsonProperty("usuarioEnvio") UserPicPayOutput sender,
                                      @JsonProperty("usuarioRecebedor") UserPicPayOutput receiver,
                                      @JsonProperty("dataTransacao") String createdAt,
                                      @JsonProperty("mensagem") String message
) {

    public static TransactionPicPayOutput buildFrom(TransactionPicPay entity) {
        return new TransactionPicPayOutput(
                entity.getId(),
                FunctionalUtils.formatDecimalNumber(entity.getAmount()),
                UserPicPayOutput.buildFrom(entity.getSender()),
                UserPicPayOutput.buildFrom(entity.getReceiver()),
                Objects.isNull(entity.getCreatedAt()) ? StringUtils.EMPTY : FunctionalUtils.formatCreationDate(entity.getCreatedAt()),
                createMessageSucess()
        );
    }

    public static String createMessageSucess() {
        return String.format("Transação realizada com sucesso em %s!", FunctionalUtils.formatCreationDateBy(LocalDateTime.now()));
    }
}
