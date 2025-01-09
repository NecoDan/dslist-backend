package com.devsuperior.dslist.picpay_challenge.dto.internal;

import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.domain.UserTypeDomain;
import com.devsuperior.dslist.utils.FunctionalUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @JsonProperty("codigo")
    private Long id;

    @JsonProperty("nome")
    private String firstName;

    @JsonProperty("sobrenome")
    private String lastName;

    @JsonProperty("documento_cpf")
    private String document;

    @JsonProperty("email")
    private String email;

    @JsonProperty("senha")
    private String password;

    @JsonProperty("saldo")
    private String balance;

    @JsonProperty("tipo")
    private UserTypeDomain userType;

    public UserDTO(User entity) {
        BeanUtils.copyProperties(entity, this);
        this.balance = FunctionalUtils.formatDecimalNumber(entity.getBalance());
    }

    public String getDocument() {
        this.document = FunctionalUtils.formatCpf(this.document);
        return this.document;
    }
}
