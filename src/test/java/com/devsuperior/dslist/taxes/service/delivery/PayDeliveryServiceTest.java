package com.devsuperior.dslist.taxes.service.delivery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PayDeliveryServiceTest {

    @InjectMocks private PayDeliveryService payDeliveryServiceMock;

    private PayDeliveryService payDeliveryService;

    @BeforeEach
    void setUp() {
        this.payDeliveryService = new PayDeliveryService(new TaxService(), new DeliveryService());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isValidPayDeliveryServiceMock() {
        assertNotNull(this.payDeliveryServiceMock);
    }

    @Test
    void isValidPayDeliveryService() {
        assertNotNull(this.payDeliveryService);
    }

    @Test
    void priceStateSantaCatarina() {
        // -- 01_Cenário
        final double purchaseOrderValue = 300D;
        final double expectedValue = 350.0;

        // -- 02_Ação
        final double scValueResult = payDeliveryService.price(purchaseOrderValue, "SC");

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
        final double scValueResult = payDeliveryService.price(purchaseOrderValue, "SP");

        // -- 03_Verificação_Validação
        assertTrue(scValueResult > 0);
        assertEquals(expectedValue, scValueResult);
        System.out.println("Valor calculado com taxa de entrega no estado SP: " + scValueResult);
    }
}