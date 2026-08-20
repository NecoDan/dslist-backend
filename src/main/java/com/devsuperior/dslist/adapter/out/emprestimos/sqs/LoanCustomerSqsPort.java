package com.devsuperior.dslist.adapter.out.emprestimos.sqs;

import com.devsuperior.dslist.adapter.out.dto.external.CustomerLoanSqsDTO;

public interface LoanCustomerSqsPort {

    void produzirMensagem(CustomerLoanSqsDTO customerLoanSqsDTO);
}
