package com.devsuperior.dslist.taxes.services.delivery;

import com.devsuperior.dslist.taxes.services.deduction.enums.TipoTaxaEstado;
import com.devsuperior.dslist.utils.enums.TipoEstado;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    public double obterTaxaPor(String codigoEstado) {
        return TipoTaxaEstado.of(codigoEstado)
                .getTaxaEntrega()
                .doubleValue();
    }

    public double obterTaxaPorEstado(TipoEstado tipoEstado) {
        return TipoTaxaEstado.of(tipoEstado)
                .getTaxaEntrega()
                .doubleValue();
    }
}
