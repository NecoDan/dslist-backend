package com.devsuperior.dslist.adapter.out.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoanSqsDTO(String type,
                         @JsonProperty("interest_rate") Double interestRate) {
}
