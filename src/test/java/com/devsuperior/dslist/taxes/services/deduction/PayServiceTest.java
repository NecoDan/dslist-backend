package com.devsuperior.dslist.taxes.services.deduction;

import com.devsuperior.dslist.taxes.services.deduction.states.MGDeductionService;
import com.devsuperior.dslist.taxes.services.deduction.states.SPDeductionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PayServiceTest {

    @InjectMocks private PayService payServiceMock;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isNotNull() {
        assertNotNull(this.payServiceMock);
    }

    @Test
    void performTaxCalculationMinasGerais() {
        // -- 01_Cenário
        final PayService payService = new PayService(new MGDeductionService());
        final double value = 1000D;
        final double expectedValue = 176.0;

        // -- 02_Ação
        final BigDecimal resultValue = payService.performTaxCalculation(value);

        // -- 03_Verificação_Validação
        assertNotNull(resultValue);
        assertEquals(expectedValue, resultValue.doubleValue());
    }

    @Test
    void performTaxCalculationByMinasGerais() {
        // -- 01_Cenário
        final PayService payService = new PayService(new MGDeductionService());
        final double value = 1000D;
        final double expectedValue = 176.0;

        // -- 02_Ação
        final double resultValue = payService.performTaxCalculationBy(value);

        // -- 03_Verificação_Validação
        assertTrue(resultValue > 0);
        assertEquals(expectedValue, resultValue);
    }

    @Test
    void performTaxCalculationSaoPaulo() {
        // -- 01_Cenário
        final PayService payService = new PayService(new SPDeductionService());
        final double value = 1000D;
        final double expectedValue = 180.0;

        // -- 02_Ação
        final BigDecimal resultValue = payService.performTaxCalculation(value);

        // -- 03_Verificação_Validação
        assertNotNull(resultValue);
        assertEquals(expectedValue, resultValue.doubleValue());
    }

    @Test
    void performTaxCalculationBySaoPaulo() {
        // -- 01_Cenário
        final PayService payService = new PayService(new SPDeductionService());
        final double value = 1000D;
        final double expectedValue = 180.0;

        // -- 02_Ação
        final double resultValue = payService.performTaxCalculationBy(value);

        // -- 03_Verificação_Validação
        assertTrue(resultValue > 0);
        assertEquals(expectedValue, resultValue);
    }
}