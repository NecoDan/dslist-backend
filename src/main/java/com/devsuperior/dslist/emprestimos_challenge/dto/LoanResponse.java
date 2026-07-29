package com.devsuperior.dslist.emprestimos_challenge.dto;

import com.devsuperior.dslist.emprestimos_challenge.domain.Loan;
import com.devsuperior.dslist.emprestimos_challenge.domain.enums.LoanType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public record LoanResponse(LoanType type,
                           @JsonProperty("interest_rate") Double interestRate) {

    @JsonIgnore
    public static LoanResponse toLoanResponseFrom(Loan loan) {
        if (Objects.nonNull(loan)) {
            return new LoanResponse(loan.getLoanType(), loan.getInterestRate());
        }
        return null;
    }

    @JsonIgnore
    public boolean isValidParams() {
        return Objects.nonNull(this.type) && Objects.nonNull(this.interestRate) && this.interestRate > 0;
    }
}
