package com.devsuperior.dslist.adapter.out.emprestimos;

import com.devsuperior.dslist.adapter.out.dto.external.CustomerLoanInputDTO;
import com.devsuperior.dslist.adapter.out.dto.external.CustomerLoanOutputDTO;

public interface LoanCustomerApiPort {

    CustomerLoanOutputDTO checkCustomerLoanEligibility(CustomerLoanInputDTO inputRequest);
}
