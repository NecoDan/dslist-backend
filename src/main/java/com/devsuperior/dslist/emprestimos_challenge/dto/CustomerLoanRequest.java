package com.devsuperior.dslist.emprestimos_challenge.dto;

import com.devsuperior.dslist.emprestimos_challenge.domain.Customer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public record CustomerLoanRequest(@NotNull @Min(value = 18) Integer age,
                                  @CPF String cpf,
                                  @NotBlank String name,
                                  @NotNull @Min(value = 1000) BigDecimal income,
                                  @NotBlank String location) {

    public Customer toCustomer() {
        return new Customer(age, cpf, name, income, location);
    }
}
