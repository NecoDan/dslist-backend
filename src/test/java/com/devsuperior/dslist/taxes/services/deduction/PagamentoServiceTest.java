package com.devsuperior.dslist.taxes.services.deduction;

import com.devsuperior.dslist.taxes.services.deduction.states.MGCalculadoraDeducaoService;
import com.devsuperior.dslist.taxes.services.deduction.states.SPCalculadoraDeducaoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @InjectMocks private PagamentoService pagamentoServiceMock;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isNotNull() {
        assertNotNull(this.pagamentoServiceMock);
    }

    @Test
    void performTaxCalculationMinasGerais() {
        // -- 01_Cenário
        final PagamentoService pagamentoService = new PagamentoService(new MGCalculadoraDeducaoService());
        final double value = 1000D;
        final double expectedValue = 176.0;

        // -- 02_Ação
        final BigDecimal resultValue = pagamentoService.efetuarCalculoImposto(value);

        // -- 03_Verificação_Validação
        assertNotNull(resultValue);
        assertEquals(expectedValue, resultValue.doubleValue());
    }

    @Test
    void performTaxCalculationByMinasGerais() {
        // -- 01_Cenário
        final PagamentoService pagamentoService = new PagamentoService(new MGCalculadoraDeducaoService());
        final double value = 1000D;
        final double expectedValue = 176.0;

        // -- 02_Ação
        final double resultValue = pagamentoService.efetuarCalculoImpostoPor(value);

        // -- 03_Verificação_Validação
        assertTrue(resultValue > 0);
        assertEquals(expectedValue, resultValue);
    }

    @Test
    void performTaxCalculationSaoPaulo() {
        // -- 01_Cenário
        final PagamentoService pagamentoService = new PagamentoService(new SPCalculadoraDeducaoService());
        final double value = 1000D;
        final double expectedValue = 180.0;

        // -- 02_Ação
        final BigDecimal resultValue = pagamentoService.efetuarCalculoImposto(value);

        // -- 03_Verificação_Validação
        assertNotNull(resultValue);
        assertEquals(expectedValue, resultValue.doubleValue());
    }

    @Test
    void performTaxCalculationBySaoPaulo() {
        // -- 01_Cenário
        final PagamentoService pagamentoService = new PagamentoService(new SPCalculadoraDeducaoService());
        final double value = 1000D;
        final double expectedValue = 180.0;

        // -- 02_Ação
        final double resultValue = pagamentoService.efetuarCalculoImpostoPor(value);

        // -- 03_Verificação_Validação
        assertTrue(resultValue > 0);
        assertEquals(expectedValue, resultValue);
    }
}