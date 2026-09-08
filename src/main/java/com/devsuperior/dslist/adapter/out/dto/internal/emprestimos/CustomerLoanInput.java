package com.devsuperior.dslist.adapter.out.dto.internal.emprestimos;

import com.devsuperior.dslist.adapter.out.dto.external.emprestimos.CustomerLoanInputDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public record CustomerLoanInput(@NotNull @Min(value = 18) Integer age,
                                @CPF String cpf,
                                @NotBlank String name,
                                @NotNull @Min(value = 1000) BigDecimal income,
                                @NotBlank String location) {

    public CustomerLoanInputDTO toInputDTO() {
        return new CustomerLoanInputDTO(age, cpf, name, income, location);
    }
}
