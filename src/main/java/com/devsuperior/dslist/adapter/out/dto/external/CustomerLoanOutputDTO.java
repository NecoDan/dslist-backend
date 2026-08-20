package com.devsuperior.dslist.adapter.out.dto.external;

import java.util.List;

public record CustomerLoanOutputDTO(
        String customerIdentifier,
        String customer,
        List<LoanOutputDTO> loans
) {
}
