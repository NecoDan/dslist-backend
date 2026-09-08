package com.devsuperior.dslist.adapter.out.dto.external.emprestimos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoanOutputDTO(String type,
                            @JsonProperty("interest_rate") Double interestRate) {
}
