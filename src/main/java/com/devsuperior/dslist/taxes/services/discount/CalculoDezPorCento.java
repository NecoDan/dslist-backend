package com.devsuperior.dslist.taxes.services.discount;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class CalculoDezPorCento implements CalculoPercentual {

    private double valorCalculado = BigDecimal.ZERO.doubleValue();
    private static final double VALOR_DEZ_PORCENTO = 10.0;

    @Override
    public void calling() {
        System.out.println("\n CalculoDezPorCento calling...");
        log.info("\n CalculoDezPorCento calling...");
    }

    @Override
    public void data() {
        log.info("\n CalculoDezPorCento data: {}.", this.valorCalculado);
        System.out.printf("\n CalculoDezPorCento data: %f.", this.valorCalculado);
    }

    @Override
    public double calcular(double valor) {
        this.valorCalculado = (valor * VALOR_DEZ_PORCENTO / 100);
        data();
        return this.valorCalculado;
    }
}
