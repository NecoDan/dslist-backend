package com.devsuperior.dslist.taxes.services.deduction.states;

import com.devsuperior.dslist.taxes.services.deduction.DeductionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class SPDeductionService implements DeductionService {

    private static final double VALUE_RATE_TAX_STATE_SP = 0.10;

    @Override
    public double deduction(double amount) {
        return amount * VALUE_RATE_TAX_STATE_SP;
    }

    @Override
    public BigDecimal deductionBy(double amount) {
        return BigDecimal.valueOf(deduction(amount));
    }

    @Override
    public double deduction(BigDecimal amount) {
        if (Objects.isNull(amount))
            throw new IllegalArgumentException("Value provided to calculate the tax deduction is invalid and/or non-existent (null).");

        return deduction(amount.doubleValue());
    }
}
