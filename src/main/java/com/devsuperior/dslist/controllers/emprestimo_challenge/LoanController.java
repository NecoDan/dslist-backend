package com.devsuperior.dslist.controllers.emprestimo_challenge;

import com.devsuperior.dslist.emprestimos_challenge.dto.CustomerLoanRequest;
import com.devsuperior.dslist.emprestimos_challenge.dto.CustomerLoanResponse;
import com.devsuperior.dslist.emprestimos_challenge.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping(value = "customer-loans")
    public ResponseEntity<CustomerLoanResponse> customersLoans(CustomerLoanRequest request) {
        return ResponseEntity.ok().body(loanService.checkLoanEligibility(request));
    }
}
