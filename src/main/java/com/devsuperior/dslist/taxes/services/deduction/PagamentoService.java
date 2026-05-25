package com.devsuperior.dslist.taxes.services.deduction;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PagamentoService {

    private CalculadoraDeducaoService calculadoraDeducaoService;

    public PagamentoService() {
        this(null);
    }

    public PagamentoService(CalculadoraDeducaoService calculadoraDeducaoService) {
        this.calculadoraDeducaoService = calculadoraDeducaoService;
    }

    public double efetuarCalculoImpostoPor(double valor) {
        valor = valor - calculadoraDeducaoService.calcularValorDeducao(valor);
        return valor * 0.2;
    }

    public BigDecimal efetuarCalculoImposto(double valor) {
        return BigDecimal.valueOf(this.efetuarCalculoImpostoPor(valor));
    }
}
