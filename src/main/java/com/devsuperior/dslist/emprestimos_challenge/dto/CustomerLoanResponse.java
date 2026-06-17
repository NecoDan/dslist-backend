package com.devsuperior.dslist.emprestimos_challenge.dto;

import java.util.List;

public record CustomerLoanResponse(String customer,
                                   List<LoanResponse> loans) {
}
