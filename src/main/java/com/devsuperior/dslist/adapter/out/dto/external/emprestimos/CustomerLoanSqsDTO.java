package com.devsuperior.dslist.adapter.out.dto.external.emprestimos;

import com.devsuperior.dslist.adapter.out.dto.internal.emprestimos.CustomerLoanOutput;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

public record CustomerLoanSqsDTO(String customerIdentifier,
                                 List<LoanSqsDTO> loans) {

    public static CustomerLoanSqsDTO fromOutput(CustomerLoanOutput customerLoanOutput) {
        List<LoanSqsDTO> loansList = CollectionUtils.isEmpty(customerLoanOutput.loans())
                ? Collections.emptyList()
                : customerLoanOutput.loans()
                .stream()
                .map(loanOutput -> new LoanSqsDTO(loanOutput.type(), loanOutput.interestRate()))
                .toList();

        return new CustomerLoanSqsDTO(
                customerLoanOutput.customerIdentifier(),
                loansList
        );
    }
}
