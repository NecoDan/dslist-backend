package com.devsuperior.dslist.emprestimos_challenge.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    private Customer customer;

    private boolean isCustomerValid() {
        return Objects.nonNull(this.customer);
    }

    public boolean isPersonalLoanAvailable() {
        return isCustomerValid() && this.customer.isIncomeEqualOrLowerThan(BigDecimal.valueOf(3000.0));
    }
}
