package com.devsuperior.dslist.picpay_challenge.dto.internal;

import com.devsuperior.dslist.picpay_challenge.domain.Transaction;
import com.devsuperior.dslist.utils.FunctionalUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionResponseDTO implements Serializable {

    @JsonProperty("codigo")
    private Long id;

    @JsonProperty("valor")
    private String amount;

    @JsonProperty("usuarioEnvio")
    private UserResponseDTO sender;

    @JsonProperty("usuarioRecebedor")
    private UserResponseDTO receiver;

    @JsonProperty("dataTransacao")
    private String createdAt;

    @JsonProperty("mensagem")
    private String message;

    public TransactionResponseDTO(Transaction entity){
        BeanUtils.copyProperties(entity, this);

        this.amount = FunctionalUtils.formatDecimalNumber(entity.getAmount());
        this.sender = new UserResponseDTO(entity.getSender());
        this.receiver = new UserResponseDTO(entity.getReceiver());
        this.createdAt = Objects.isNull(entity.getCreatedAt()) ? StringUtils.EMPTY : FunctionalUtils.formatCreationDate(entity.getCreatedAt());
    }

    public TransactionResponseDTO createMessageSucess(){
        this.message = String.format("Transação realizada com sucesso em %s!", FunctionalUtils.formatCreationDateBy(LocalDateTime.now()));
        return this;
    }
}
