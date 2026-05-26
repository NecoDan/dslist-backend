package com.devsuperior.dslist.taxes.services.delivery;

import com.devsuperior.dslist.taxes.services.deduction.enums.TipoTaxaEstado;
import com.devsuperior.dslist.utils.enums.TipoEstado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


class PagamentoDeliveryServiceV1Test {

    @Mock
    private TaxaEntregaService taxaEntregaService;

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private PagamentoDeliveryService pagamentoDeliveryServiceMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isValidPayDeliveryServiceMock() {
        assertNotNull(this.pagamentoDeliveryServiceMock);
    }

    @Test
    void priceStateSantaCatarina() {
        // -- 01_Cenário
        final var tipoEstado = TipoEstado.SC;
        final var tipoTaxaEstado = TipoTaxaEstado.of(tipoEstado);
        final var valorTaxaEntregaEstado = tipoTaxaEstado.getTaxaEntrega().doubleValue();

        final double purchaseOrderValue = 300D;
        final double valorTaxaEntregaProduto = 320.0D;
        final double expectedValue = 640.0D;

        when(deliveryService.obterTaxaPor(anyString()))
                .thenReturn(valorTaxaEntregaEstado);

        when(taxaEntregaService.realizarCalculoTaxaEntrega(anyDouble()))
                .thenReturn(valorTaxaEntregaProduto);

        // -- 02_Ação
        final double scValueResult = pagamentoDeliveryServiceMock.calcularPrecoFinal(
                purchaseOrderValue,
                tipoEstado.getCodigo()
        );

        // -- 03_Verificação_Validação
        assertTrue(scValueResult > 0);
        assertEquals(expectedValue, scValueResult);
        System.out.println("Valor calculado com taxa de entrega no estado SC: " + scValueResult);
    }

    @Test
    void priceStateSaoPaulo() {
        // -- 01_Cenário
        final var tipoEstado = TipoEstado.SP;
        final var tipoTaxaEstado = TipoTaxaEstado.of(tipoEstado);
        final var valorTaxaEntregaEstado = tipoTaxaEstado.getTaxaEntrega().doubleValue();

        final double purchaseOrderValue = 1000D;
        final double valorTaxaEntregaProduto = 300.0D;
        final double expectedValue = 1310.0D;

        when(deliveryService.obterTaxaPor(anyString()))
                .thenReturn(valorTaxaEntregaEstado);

        when(taxaEntregaService.realizarCalculoTaxaEntrega(anyDouble()))
                .thenReturn(valorTaxaEntregaProduto);

        // -- 02_Ação
        final double scValueResult = pagamentoDeliveryServiceMock.calcularPrecoFinal(
                purchaseOrderValue,
                tipoEstado.getCodigo()
        );

        // -- 03_Verificação_Validação
        assertTrue(scValueResult > 0);
        assertEquals(expectedValue, scValueResult);
        System.out.println("Valor calculado com taxa de entrega no estado SP: " + scValueResult);
    }
}