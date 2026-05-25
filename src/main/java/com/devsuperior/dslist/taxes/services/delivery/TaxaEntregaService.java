package com.devsuperior.dslist.taxes.services.delivery;

import org.springframework.stereotype.Service;

@Service
public class TaxaEntregaService {

    private static final double PERCENTUAL_TAXA_ENTREGA = 0.10;

    public double realizarCalculoTaxaEntrega(double value) {
        return value * PERCENTUAL_TAXA_ENTREGA;
    }
}
