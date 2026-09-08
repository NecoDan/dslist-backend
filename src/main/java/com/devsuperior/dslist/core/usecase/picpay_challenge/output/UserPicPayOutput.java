package com.devsuperior.dslist.core.usecase.picpay_challenge.output;

import com.devsuperior.dslist.core.domain.picpay_challenge.TypeUserPicPay;
import com.devsuperior.dslist.core.domain.picpay_challenge.UserPicPay;
import com.devsuperior.dslist.utils.FunctionalUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Builder
public record UserPicPayOutput(@JsonProperty("codigo") Long id,
                               @JsonProperty("nome") String firstName,
                               @JsonProperty("sobrenome") String lastName,
                               @JsonProperty("documentoCpfCnpj") String document,
                               @JsonProperty("email") String email,
                               @JsonIgnore @JsonProperty("senha") String password,
                               @JsonProperty("saldo") String balance,
                               @JsonProperty("tipo") TypeUserPicPay userType,
                               @JsonProperty("dataCriacao") String createdAt
) {
    public static UserPicPayOutput buildFrom(UserPicPay entity) {
        return new UserPicPayOutput(entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                FunctionalUtils.formatCpf(entity.getDocument()),
                entity.getEmail(),
                entity.getPassword(),
                FunctionalUtils.formatDecimalNumber(entity.getBalance()),
                entity.getUserType(),
                Objects.isNull(entity.getCreatedAt()) ? StringUtils.EMPTY : FunctionalUtils.formatCreationDate(entity.getCreatedAt())
        );
    }

    //    public String getDocument() {
    //        this.document = FunctionalUtils.formatCpf(this.document);
    //        return this.document;
    //    }
}
