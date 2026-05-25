package com.devsuperior.dslist.taxes.services.delivery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(PagamentoDeliveryService.class)
class PagamentoDeliveryServiceV3Test {

    @MockBean
    private TaxaEntregaService taxaEntregaService;

    @MockBean
    private DeliveryService deliveryService;

    private PagamentoDeliveryService pagamentoDeliveryServiceMock;

    @BeforeEach
    void setUp() {
        this.pagamentoDeliveryServiceMock = new PagamentoDeliveryService(new TaxaEntregaService(), new DeliveryService());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isValidPagamentoDeliveryServiceMock() {
        assertNotNull(this.pagamentoDeliveryServiceMock);
    }

    @Test
    void priceStateSantaCatarina() {
        // -- 01_Cenário
        final double purchaseOrderValue = 300D;
        final double expectedValue = 350.0;

        // -- 02_Ação
        final double scValueResult = pagamentoDeliveryServiceMock.calcularPrecoFinal(purchaseOrderValue, "SC");

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
        final double scValueResult = pagamentoDeliveryServiceMock.calcularPrecoFinal(purchaseOrderValue, "SP");

        // -- 03_Verificação_Validação
        assertTrue(scValueResult > 0);
        assertEquals(expectedValue, scValueResult);
        System.out.println("Valor calculado com taxa de entrega no estado SP: " + scValueResult);
    }
}