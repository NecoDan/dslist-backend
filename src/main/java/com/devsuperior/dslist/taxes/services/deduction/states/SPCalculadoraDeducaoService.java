package com.devsuperior.dslist.taxes.services.deduction.states;

import com.devsuperior.dslist.taxes.services.deduction.CalculadoraDeducaoService;
import com.devsuperior.dslist.taxes.services.deduction.enums.TipoTaxaEstado;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class SPCalculadoraDeducaoService implements CalculadoraDeducaoService {

    @Override
    public double calcularValorDeducao(double valor) {
        return valor * TipoTaxaEstado.SP.getTaxaImposto().doubleValue();
    }

    @Override
    public BigDecimal calcularValorDeducaoPor(double valor) {
        return BigDecimal.valueOf(calcularValorDeducao(valor));
    }

    @Override
    public double calcularValorDeducao(BigDecimal valor) {
        if (Objects.isNull(valor))
            throw new IllegalArgumentException("Value provided to calculate the tax deduction is invalid and/or non-existent (null).");
        return calcularValorDeducao(valor.doubleValue());
    }
}
