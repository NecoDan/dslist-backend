package com.devsuperior.dslist.taxes.services.delivery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PayDeliveryServiceV1Test {

    @Autowired private PayDeliveryService payDeliveryServiceMock;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isValidPayDeliveryServiceMock() {
        assertNotNull(this.payDeliveryServiceMock);
    }

    @Test
    void priceStateSantaCatarina() {
        // -- 01_Cenário
        final double purchaseOrderValue = 300D;
        final double expectedValue = 350.0;

        // -- 02_Ação
        final double scValueResult = payDeliveryServiceMock.price(purchaseOrderValue, "SC");

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
        final double scValueResult = payDeliveryServiceMock.price(purchaseOrderValue, "SP");

        // -- 03_Verificação_Validação
        assertTrue(scValueResult > 0);
        assertEquals(expectedValue, scValueResult);
        System.out.println("Valor calculado com taxa de entrega no estado SP: " + scValueResult);
    }
}