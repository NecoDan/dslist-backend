package com.devsuperior.dslist.adapter.out.dto.external.emprestimos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public record CustomerLoanInputDTO(
        @NotNull @Min(value = 18) Integer age,
        @CPF String cpf,
        @NotBlank String name,
        @NotNull @Min(value = 1000) BigDecimal income,
        @NotBlank String location
) {
}
