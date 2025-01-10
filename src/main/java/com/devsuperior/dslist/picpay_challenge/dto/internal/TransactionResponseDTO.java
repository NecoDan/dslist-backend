package com.devsuperior.dslist.picpay_challenge.dto.internal;

import com.devsuperior.dslist.picpay_challenge.domain.Transaction;
import com.devsuperior.dslist.utils.FunctionalUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionResponseDTO implements Serializable {

    @JsonProperty("codigo")
    private Long id;

    @JsonProperty("valor")
    private BigDecimal amount;

    @JsonProperty("usuarioEnvio")
    private UserResponseDTO sender;

    @JsonProperty("usuarioRecebedor")
    private UserResponseDTO receiver;

    @JsonProperty("dataTransacao")
    private LocalDateTime createdAt;

    @JsonProperty("mensagem")
    private String message;

    public TransactionResponseDTO(Transaction entity){
        BeanUtils.copyProperties(entity, this);
        this.sender = new UserResponseDTO(entity.getSender());
        this.receiver = new UserResponseDTO(entity.getReceiver());
    }

    public TransactionResponseDTO createMessageSucess(){
        this.message = String.format("Transação realizada com sucesso em %s!", FunctionalUtils.formatCreationDateBy(LocalDateTime.now()));
        return this;
    }
}
