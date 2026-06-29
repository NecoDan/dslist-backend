package com.devsuperior.dslist.emprestimos_challenge.util;

import com.devsuperior.dslist.emprestimos_challenge.domain.Customer;
import com.devsuperior.dslist.emprestimos_challenge.domain.Loan;

import java.math.BigDecimal;

public final class LoanChallengeFactory {

    private LoanChallengeFactory() {
        throw new UnsupportedOperationException("Class utility. LoanChallengeFactory");
    }

    public static Customer createCustomerWithIncome(BigDecimal income) {
        var customer = new Customer();
        customer.setIncome(income);

        return customer;
    }

    public static Customer createCustomerWithAge(Integer age) {
        var customer = new Customer();
        customer.setAge(age);

        return customer;
    }

    public static Customer createCustomerWithLocation(String location) {
        var customer = new Customer();
        customer.setLocation(location);

        return customer;
    }

    public static Loan createLoanDomainIncomeIdadePositivo(String location) {
        var customer = Customer.builder()
                .income(BigDecimal.valueOf(100.0))
                .age(30)
                .location(location)
                .build();

        return Loan.builder()
                .customer(customer)
                .build();
    }

    public static Loan createLoanDomainIncomeMaiorQue3000(String location) {
        var customer = Customer.builder()
                .income(BigDecimal.valueOf(3500.0))
                .age(30)
                .location(location)
                .build();

        return Loan.builder()
                .customer(customer)
                .build();
    }

    public static Loan createLoanDomainIncomeIdadeNegativo(String location) {
        var customer = Customer.builder()
                .income(BigDecimal.valueOf(-200.0))
                .age(10)
                .location(location)
                .build();

        return Loan.builder()
                .customer(customer)
                .build();
    }

}
