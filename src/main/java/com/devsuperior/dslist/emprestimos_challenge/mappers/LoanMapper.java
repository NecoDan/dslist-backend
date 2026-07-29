package com.devsuperior.dslist.emprestimos_challenge.mappers;

import com.devsuperior.dslist.emprestimos_challenge.domain.Customer;
import com.devsuperior.dslist.emprestimos_challenge.domain.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LoanMapper {

    Loan toLoan(Customer customer);
}
