package com.devsuperior.dslist.taxes.services.deduction;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PayService {

    private DeductionService deductionService;

    public PayService() {
        this(null);
    }

    public PayService(DeductionService deductionService) {
        this.deductionService = deductionService;
    }

    public double performTaxCalculationBy(double amount) {
        amount = amount - deductionService.deduction(amount);
        return amount * 0.2;
    }

    public BigDecimal performTaxCalculation(double amount) {
        return BigDecimal.valueOf(this.performTaxCalculationBy(amount));
    }

}
