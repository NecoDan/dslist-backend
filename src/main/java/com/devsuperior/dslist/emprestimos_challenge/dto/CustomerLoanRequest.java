package com.devsuperior.dslist.emprestimos_challenge.dto;

import java.math.BigDecimal;

public record CustomerLoanRequest(Integer age,
                                  String cpf,
                                  String name,
                                  BigDecimal income,
                                  String location) {
}
