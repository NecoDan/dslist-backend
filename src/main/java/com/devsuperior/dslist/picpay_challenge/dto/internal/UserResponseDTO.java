package com.devsuperior.dslist.picpay_challenge.dto.internal;

import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.domain.UserTypeDomain;
import com.devsuperior.dslist.utils.FunctionalUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    @JsonProperty("codigo")
    private Long id;

    @JsonProperty("nome")
    private String firstName;

    @JsonProperty("sobrenome")
    private String lastName;

    @JsonProperty("documentoCpfCnpj")
    private String document;

    @JsonProperty("email")
    private String email;

    @JsonIgnore
    @JsonProperty("senha")
    private String password;

    @JsonProperty("saldo")
    private String balance;

    @JsonProperty("tipo")
    private UserTypeDomain userType;

    @JsonProperty("dataCriacao")
    private String createdAt;

    public UserResponseDTO(User entity) {
        BeanUtils.copyProperties(entity, this);
        this.balance = FunctionalUtils.formatDecimalNumber(entity.getBalance());
        this.createdAt = Objects.isNull(entity.getCreatedAt()) ? StringUtils.EMPTY : FunctionalUtils.formatCreationDate(entity.getCreatedAt());
    }

    public String getDocument() {
        this.document = FunctionalUtils.formatCpf(this.document);
        return this.document;
    }
}
