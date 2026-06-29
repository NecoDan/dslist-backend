package com.devsuperior.dslist.emprestimos_challenge.mappers;

import com.devsuperior.dslist.emprestimos_challenge.domain.Customer;
import com.devsuperior.dslist.emprestimos_challenge.domain.Loan;
import com.devsuperior.dslist.emprestimos_challenge.dto.LoanResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    Loan toLoan(Customer customer);

    LoanResponse toLoanResponseFrom(Loan loan);


}
