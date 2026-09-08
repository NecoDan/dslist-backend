package com.devsuperior.dslist.adapter.out.sqs.emprestimo_challenge;

import com.devsuperior.dslist.adapter.out.dto.external.emprestimos.CustomerLoanSqsDTO;

public interface LoanCustomerSqsPort {

    void produzirMensagem(CustomerLoanSqsDTO customerLoanSqsDTO);
}
