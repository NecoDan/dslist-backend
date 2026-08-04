package com.devsuperior.dslist.adapter.out.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoanOutput(String type,
                         @JsonProperty("interest_rate") Double interestRate) {
}
