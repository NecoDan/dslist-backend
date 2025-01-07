package com.devsuperior.dslist.taxes.services.deduction;

import java.math.BigDecimal;

public interface DeductionService {

    double deduction(double amount);

    BigDecimal deductionBy(double amount);

    double deduction(BigDecimal amount);

}
