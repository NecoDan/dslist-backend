package com.devsuperior.dslist.adapter.out.api.emprestimos;

import com.devsuperior.dslist.adapter.out.dto.external.emprestimos.CustomerLoanInputDTO;
import com.devsuperior.dslist.adapter.out.dto.external.emprestimos.CustomerLoanOutputDTO;

public interface LoanCustomerApiPort {
    CustomerLoanOutputDTO checkCustomerLoanEligibility(CustomerLoanInputDTO inputRequest);
}
