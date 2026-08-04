package com.devsuperior.dslist.adapter.out.emprestimos;

import com.devsuperior.dslist.adapter.out.dto.internal.CustomerLoanInput;
import com.devsuperior.dslist.adapter.out.dto.internal.CustomerLoanOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanCustomerUseCase {

    private final LoanCustomerApiPort loanCustomerApiPort;

    public CustomerLoanOutput checkCustomerLoanEligibility(CustomerLoanInput input) {
        return CustomerLoanOutput.fromDTO(
                loanCustomerApiPort.checkCustomerLoanEligibility(input.toInputDTO())
        );
    }
}
