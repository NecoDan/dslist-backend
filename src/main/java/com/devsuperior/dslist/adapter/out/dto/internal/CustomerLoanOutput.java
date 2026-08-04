package com.devsuperior.dslist.adapter.out.dto.internal;

import com.devsuperior.dslist.adapter.out.dto.external.CustomerLoanOutputDTO;

import java.util.List;

public record CustomerLoanOutput(String customer,
                                 List<LoanOutput> loans) {

    public static CustomerLoanOutput fromDTO(CustomerLoanOutputDTO dto) {
        List<LoanOutput> loans = dto.loans() != null ?
                dto.loans().stream()
                        .map(loan -> new LoanOutput(loan.type(), loan.interestRate()))
                        .toList() : List.of();

        return new CustomerLoanOutput(dto.customer(), loans);
    }
}
