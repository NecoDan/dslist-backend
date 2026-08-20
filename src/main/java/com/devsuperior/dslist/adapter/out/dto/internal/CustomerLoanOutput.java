package com.devsuperior.dslist.adapter.out.dto.internal;

import com.devsuperior.dslist.adapter.out.dto.external.CustomerLoanOutputDTO;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

public record CustomerLoanOutput(
        String customerIdentifier,
        String customer,
        List<LoanOutput> loans) {

    public static CustomerLoanOutput fromDTO(CustomerLoanOutputDTO dto) {
        List<LoanOutput> loans = CollectionUtils.isEmpty(dto.loans())
                ? Collections.emptyList()
                : dto.loans().stream()
                .map(loan -> new LoanOutput(loan.type(), loan.interestRate()))
                .toList();

        return new CustomerLoanOutput(
                dto.customerIdentifier(),
                dto.customer(),
                loans
        );
    }
}
