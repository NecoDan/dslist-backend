package com.devsuperior.dslist.adapter.out.emprestimos;

import com.devsuperior.dslist.adapter.out.dto.external.CustomerLoanSqsDTO;
import com.devsuperior.dslist.adapter.out.dto.internal.CustomerLoanInput;
import com.devsuperior.dslist.adapter.out.dto.internal.CustomerLoanOutput;
import com.devsuperior.dslist.adapter.out.emprestimos.api.LoanCustomerApiPort;
import com.devsuperior.dslist.adapter.out.emprestimos.sqs.LoanCustomerSqsPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoanCustomerUseCase {

    private final LoanCustomerApiPort loanCustomerApiPort;
    private final LoanCustomerSqsPort loanCustomerSqsPort;

    public CustomerLoanOutput checkCustomerLoanEligibility(CustomerLoanInput input) {
        log.info("Checking loan eligibility for customer: {}", input.name());

        final var outPut = CustomerLoanOutput.fromDTO(
                loanCustomerApiPort.checkCustomerLoanEligibility(
                        input.toInputDTO()
                )
        );

        loanCustomerSqsPort.produzirMensagem(CustomerLoanSqsDTO.fromOutput(outPut));

        log.info("Loan eligibility check completed for customer: {} - {}. Available loans: {}",
                outPut.customerIdentifier(), outPut.customer(), outPut.loans()
        );

        return outPut;
    }
}
