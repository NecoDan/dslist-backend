package com.devsuperior.dslist.taxes.services.discount;

import java.math.BigDecimal;

public class CalculoVintePorCento implements CalculoPercentual {

    private double valorCalculado = BigDecimal.ZERO.doubleValue();
    private static final double VALOR_VINTE_PORCENTO = 20.0;

    @Override
    public void calling() {
        System.out.println("\n CalculoVintePorCento calling...");
    }

    @Override
    public void data() {
        System.out.printf("\n CalculoVintePorCento data: %f", this.valorCalculado);
    }

    @Override
    public double calcular(double valor) {
        this.valorCalculado = (valor * VALOR_VINTE_PORCENTO / 100);
        data();
        return this.valorCalculado;
    }
}
