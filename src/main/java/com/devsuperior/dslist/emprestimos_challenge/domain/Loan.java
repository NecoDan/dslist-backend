package com.devsuperior.dslist.emprestimos_challenge.domain;


import com.devsuperior.dslist.emprestimos_challenge.domain.enums.LoanTypePercent;
import com.devsuperior.dslist.exceptions.LoanNotAvailableException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Loan implements Serializable {

    @Serial private static final long serialVersionUID = 1666768725052453109L;

    private static final double VALOR_TRES_MIL = 3000.0D;
    private static final double VALOR_CINCO_MIL = 5000.0D;

    private Customer customer;
    private LoanTypePercent loanTypePercent;

    private boolean isCustomerValid() {
        return Objects.nonNull(this.customer);
    }

    public boolean isPersonalLoanAvailable() {
        return isCustomerValid()
                && this.customer.isIncomeEqualOrLowerThan(BigDecimal.valueOf(VALOR_TRES_MIL))
                || isPersonalLoanNotAvailableSecondTerms();
    }

    public boolean isPersonalLoanNotAvailableSecondTerms() {
        return isCustomerValid()
                && this.customer.isIncomeBetween(BigDecimal.valueOf(VALOR_TRES_MIL), BigDecimal.valueOf(VALOR_CINCO_MIL))
                && this.customer.isAgeLowerThan(30)
                && this.customer.isFromLocation("SP");
    }

    public double getPersonalLoanInterestRate() {
        if (isPersonalLoanAvailable()) {
            return LoanTypePercent.PERSONAL_PERCENT.getPercent();
        }

        throw new LoanNotAvailableException("Personal loan is not available for this customer.");
    }
}
