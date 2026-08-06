package com.devsuperior.dslist.adapter.out.emprestimos;

import com.devsuperior.dslist.adapter.out.client.feign.LoanCustomerClient;
import com.devsuperior.dslist.adapter.out.dto.external.CustomerLoanInputDTO;
import com.devsuperior.dslist.adapter.out.dto.external.CustomerLoanOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanCustomerApiAdapter implements LoanCustomerApiPort {

    private final LoanCustomerClient loanCustomerClient;

    @Override
    @Retryable(interceptor = "retryOperationsInterceptorExternal")
    public CustomerLoanOutputDTO checkCustomerLoanEligibility(CustomerLoanInputDTO inputRequest) {
        return loanCustomerClient.checkCustomerLoanEligibility(inputRequest);
    }
}
