package com.devsuperior.dslist.taxes.services.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoDeliveryService {

    @Autowired
    TaxaEntregaService taxaEntregaService;

    @Autowired
    DeliveryService deliveryService;

    public PagamentoDeliveryService(TaxaEntregaService taxaEntregaService,
                                    DeliveryService deliveryService) {

        this.taxaEntregaService = taxaEntregaService;
        this.deliveryService = deliveryService;
    }

    public double calcularPrecoFinal(double valorProduto,
                                     String codigoEstado) {

        final double valorTaxaEntregaEstado = deliveryService.obterTaxaPor(codigoEstado);
        final double valorTaxaEntrega = taxaEntregaService.realizarCalculoTaxaEntrega(valorProduto);
        return valorProduto + valorTaxaEntregaEstado + valorTaxaEntrega;
    }
}
