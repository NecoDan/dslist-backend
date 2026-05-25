package com.devsuperior.dslist.taxes.services.deduction;

import java.math.BigDecimal;

public interface CalculadoraDeducaoService {

    double calcularValorDeducao(double amount);

    BigDecimal calcularValorDeducaoPor(double amount);

    double calcularValorDeducao(BigDecimal amount);

}
