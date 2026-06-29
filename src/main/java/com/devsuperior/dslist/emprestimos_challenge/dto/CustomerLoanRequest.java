package com.devsuperior.dslist.emprestimos_challenge.dto;

import com.devsuperior.dslist.emprestimos_challenge.domain.Customer;

import java.math.BigDecimal;

public record CustomerLoanRequest(Integer age,
                                  String cpf,
                                  String name,
                                  BigDecimal income,
                                  String location) {

    public Customer toCustomer() {
        return new Customer(age, cpf, name, income, location);
    }
}
