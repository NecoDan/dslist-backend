package com.devsuperior.dslist.picpay_challenge.dto.request;

import com.devsuperior.dslist.picpay_challenge.domain.UserTypeDomain;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    @JsonProperty("nome")
    private String firstName;

    @JsonProperty("sobrenome")
    private String lastName;

    @JsonProperty("documento")
    private String document;

    @JsonProperty("email")
    private String email;

    @JsonProperty("senha")
    private String password;

    @JsonProperty("saldo")
    private BigDecimal balance;

    @JsonProperty("tipo")
    private UserTypeDomain userType;
}
