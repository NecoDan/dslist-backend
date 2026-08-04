package com.devsuperior.dslist.adapter.out.dto.external;

import java.util.List;

public record CustomerLoanOutputDTO(
        String customer,
        List<LoanOutputDTO> loans
) {
}
