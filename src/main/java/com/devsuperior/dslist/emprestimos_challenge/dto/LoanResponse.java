package com.devsuperior.dslist.emprestimos_challenge.dto;

import com.devsuperior.dslist.emprestimos_challenge.domain.enums.LoanType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record LoanResponse(LoanType type,
                           @JsonProperty("interest_rate") BigDecimal interestRate) {
}
