package com.devsuperior.dslist.taxes.services.delivery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PagamentoDeliveryServiceV2Test {

    private PagamentoDeliveryService pagamentoDeliveryServiceVar;

    @BeforeEach
    void setUp() {
        this.pagamentoDeliveryServiceVar = new PagamentoDeliveryService(new TaxaEntregaService(), new DeliveryService());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isValidPagamentoDeliveryService() {
        assertNotNull(this.pagamentoDeliveryServiceVar);
    }

    @Test
    void priceStateSantaCatarina() {
        // -- 01_Cenário
        final double purchaseOrderValue = 300D;
        final double expectedValue = 350.0;

        // -- 02_Ação
        final double scValueResult = pagamentoDeliveryServiceVar.calcularPrecoFinal(purchaseOrderValue, "SC");

        // -- 03_Verificação_Validação
        assertTrue(scValueResult > 0);
        assertEquals(expectedValue, scValueResult);
        System.out.println("Valor calculado com taxa de entrega no estado SC: " + scValueResult);
    }

    @Test
    void priceStateSaoPaulo() {
        // -- 01_Cenário
        final double purchaseOrderValue = 300D;
        final double expectedValue = 340.0;

        // -- 02_Ação
        final double scValueResult = pagamentoDeliveryServiceVar.calcularPrecoFinal(purchaseOrderValue, "SP");

        // -- 03_Verificação_Validação
        assertTrue(scValueResult > 0);
        assertEquals(expectedValue, scValueResult);
        System.out.println("Valor calculado com taxa de entrega no estado SP: " + scValueResult);
    }
}