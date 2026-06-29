package com.devsuperior.dslist.emprestimos_challenge.service;

import com.devsuperior.dslist.emprestimos_challenge.dto.CustomerLoanRequest;
import com.devsuperior.dslist.emprestimos_challenge.dto.CustomerLoanResponse;
import com.devsuperior.dslist.emprestimos_challenge.mappers.LoanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanMapper loanMapper;

    public CustomerLoanResponse checkLoanEligibility(CustomerLoanRequest request) {
        var customer = request.toCustomer();
        var loan = loanMapper.toLoan(customer);



        return new CustomerLoanResponse(loan.getCustomer().getName(), )
    }
}
