package com.devsuperior.dslist.taxes.services.deduction.states;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SPDeductionServiceTest {

    @InjectMocks SPCalculadoraDeducaoService spDeductionServiceMock;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isValidSPDeductionService() {
        assertNotNull(this.spDeductionServiceMock);
    }

    @Test
    void deduction() {
        // -- 01_Cenário
        final double value = 1000D;
        final double expectedValue = 100.0;

        // -- 02_Ação
        final double resultValue = spDeductionServiceMock.calcularValorDeducao(value);

        // -- 03_Verificação_Validação
        assertTrue(resultValue > 0);
        assertEquals(expectedValue, resultValue);
    }

    @Test
    void deductionBy() {
        // -- 01_Cenário
        final double value = 1000D;
        final double expectedValue = 100.0;

        // -- 02_Ação
        final BigDecimal resultValue = spDeductionServiceMock.calcularValorDeducaoPor(value);

        // -- 03_Verificação_Validação
        assertNotNull(resultValue);
        assertTrue(resultValue.doubleValue() > 0);
        assertEquals(expectedValue, resultValue.doubleValue());
    }

    @Test
    void testDeduction() {
        // -- 01_Cenário
        final double value = 1000D;
        final double expectedValue = 100.0;

        // -- 02_Ação
        final double resultValue = spDeductionServiceMock.calcularValorDeducao(BigDecimal.valueOf(value));

        // -- 03_Verificação_Validação
        assertTrue(resultValue > 0);
        assertEquals(expectedValue, resultValue);
    }
}