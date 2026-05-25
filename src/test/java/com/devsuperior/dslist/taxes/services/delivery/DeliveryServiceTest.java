package com.devsuperior.dslist.taxes.services.delivery;

import com.devsuperior.dslist.taxes.services.deduction.enums.TipoTaxaEstado;
import com.devsuperior.dslist.utils.enums.TipoEstado;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryServiceTest {

    @Autowired private DeliveryService deliveryService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void obterTaxaPorEstado_LowerCase() {
        // -- 01_Cenário
        final var codigoEstado = "MA";

        // -- 02_Ação
        double valor = deliveryService.obterTaxaPor(codigoEstado);

        // -- 03_Verificação_Validação
        assertTrue(valor > 0);
        assertEquals(TipoTaxaEstado.MA.getTaxaEntrega().doubleValue(), valor);
        assertEquals(TipoTaxaEstado.MA.getTaxaEntrega(), BigDecimal.valueOf(valor));
    }

    @Test
    void obterTaxaPorEstado_UpperCase() {
        // -- 01_Cenário
        final var codigoEstado = "AM";

        // -- 02_Ação
        double valor = deliveryService.obterTaxaPor(codigoEstado);

        // -- 03_Verificação_Validação
        assertTrue(valor > 0);
        assertEquals(TipoTaxaEstado.AM.getTaxaEntrega().doubleValue(), valor);
        assertEquals(TipoTaxaEstado.AM.getTaxaEntrega(), BigDecimal.valueOf(valor));
    }

    @Test
    void obterTaxaPorEstadoVia() {
        // -- 01_Cenário
        final var estado = TipoEstado.CE;

        // -- 02_Ação
        double valor = deliveryService.obterTaxaPorEstado(estado);

        // -- 03_Verificação_Validação
        assertTrue(valor > 0);
        assertEquals(TipoTaxaEstado.CE.getTaxaEntrega().doubleValue(), valor);
        assertEquals(TipoTaxaEstado.CE.getTaxaEntrega(), BigDecimal.valueOf(valor));
    }
}