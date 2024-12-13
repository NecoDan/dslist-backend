package com.devsuperior.dslist.taxes.service.delivery;

import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private static final double TAX_AMOUNT = 0.10;

    public double performTaxCalculation(double value) {
        return value * TAX_AMOUNT;
    }
}
