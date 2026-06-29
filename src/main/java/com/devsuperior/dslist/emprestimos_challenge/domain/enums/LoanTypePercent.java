package com.devsuperior.dslist.emprestimos_challenge.domain.enums;


import lombok.Getter;

@Getter
public enum LoanTypePercent {

    PERSONAL_PERCENT(LoanType.PERSONAL, 4.0),

    GUARANTEED_PERCENT(LoanType.GUARANTEED, 0.0),

    CONSIGNMENT_PERCENT(LoanType.CONSIGNMENT, 0.0);

    private final Double percent;
    private final LoanType loanType;

    LoanTypePercent(LoanType loanType, Double percent) {
        this.percent = percent;
        this.loanType = loanType;
    }
}
