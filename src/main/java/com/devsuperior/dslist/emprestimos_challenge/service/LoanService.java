package com.devsuperior.dslist.emprestimos_challenge.service;

import com.devsuperior.dslist.emprestimos_challenge.domain.Loan;
import com.devsuperior.dslist.emprestimos_challenge.dto.CustomerLoanRequest;
import com.devsuperior.dslist.emprestimos_challenge.dto.CustomerLoanResponse;
import com.devsuperior.dslist.emprestimos_challenge.dto.LoanResponse;
import com.devsuperior.dslist.emprestimos_challenge.mappers.LoanMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanMapperImpl loanMapper;

    public CustomerLoanResponse checkLoanEligibility(CustomerLoanRequest request) {
        final var loan = loanMapper.toLoan(request.toCustomer());
        return new CustomerLoanResponse(loan.getCustomer().getName(), getListOfAvailableLoans(loan));
    }

    public List<LoanResponse> getListOfAvailableLoans(Loan loan) {
        List<LoanResponse> list = new ArrayList<>();

        list.add(LoanResponse.toLoanResponseFrom(new Loan(loan.toAvailableLoansPersonal())));
        list.add(LoanResponse.toLoanResponseFrom(new Loan(loan.toAvaliableLoansGuaranteed())));
        list.add(LoanResponse.toLoanResponseFrom(new Loan(loan.toAvailableLoansConsignment())));

        return list.stream()
                .filter(Objects::nonNull)
                .filter(LoanResponse::isValidParams)
                .distinct()
                .toList();
    }
}
