package com.devsuperior.dslist.taxes.services.delivery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PayDeliveryServiceV2Test {

    private PayDeliveryService payDeliveryServiceVar;

    @BeforeEach
    void setUp() {
        this.payDeliveryServiceVar = new PayDeliveryService(new TaxService(), new DeliveryService());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isValidPayDeliveryService() {
        assertNotNull(this.payDeliveryServiceVar);
    }

    @Test
    void priceStateSantaCatarina() {
        // -- 01_Cenário
        final double purchaseOrderValue = 300D;
        final double expectedValue = 350.0;

        // -- 02_Ação
        final double scValueResult = payDeliveryServiceVar.price(purchaseOrderValue, "SC");

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
        final double scValueResult = payDeliveryServiceVar.price(purchaseOrderValue, "SP");

        // -- 03_Verificação_Validação
        assertTrue(scValueResult > 0);
        assertEquals(expectedValue, scValueResult);
        System.out.println("Valor calculado com taxa de entrega no estado SP: " + scValueResult);
    }
}