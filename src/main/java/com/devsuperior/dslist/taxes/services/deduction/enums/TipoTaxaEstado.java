package com.devsuperior.dslist.taxes.services.deduction.enums;

import com.devsuperior.dslist.utils.enums.TipoEstado;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
public enum TipoTaxaEstado {

    AC(TipoEstado.AC, BigDecimal.valueOf(0.12), BigDecimal.valueOf(50.0)),
    AL(TipoEstado.AL, BigDecimal.valueOf(0.12), BigDecimal.valueOf(30.0)),
    AP(TipoEstado.AP, BigDecimal.valueOf(0.12), BigDecimal.valueOf(50.0)),
    AM(TipoEstado.AM, BigDecimal.valueOf(0.12), BigDecimal.valueOf(50.0)),
    BA(TipoEstado.BA, BigDecimal.valueOf(0.12), BigDecimal.valueOf(30.0)),
    CE(TipoEstado.CE, BigDecimal.valueOf(0.12), BigDecimal.valueOf(30.0)),
    DF(TipoEstado.DF, BigDecimal.valueOf(0.12), BigDecimal.valueOf(20.0)),
    ES(TipoEstado.ES, BigDecimal.valueOf(0.12), BigDecimal.valueOf(10.0)),
    GO(TipoEstado.GO, BigDecimal.valueOf(0.12), BigDecimal.valueOf(20.0)),
    MA(TipoEstado.MA, BigDecimal.valueOf(0.12), BigDecimal.valueOf(30.0)),
    MT(TipoEstado.MT, BigDecimal.valueOf(0.12), BigDecimal.valueOf(20.0)),
    MS(TipoEstado.MS, BigDecimal.valueOf(0.12), BigDecimal.valueOf(20.0)),
    MG(TipoEstado.MG, BigDecimal.valueOf(0.12), BigDecimal.valueOf(10.0)),
    //    PA("PA", "Pará"),
//    PB("PB", "Paraíba"),
//    PR("PR", "Paraná"),
//    PE("PE", "Pernambuco"),
//    PI("PI", "Piauí"),
//    RJ("RJ", "Rio de Janeiro"),
//    RN("RN", "Rio Grande do Norte"),
//    RS("RS", "Rio Grande do Sul"),
//    RO("RO", "Rondônia"),
//    RR("RR", "Roraima"),
    SC(TipoEstado.SC, BigDecimal.valueOf(0.20), BigDecimal.valueOf(20.0)),
    SP(TipoEstado.SP, BigDecimal.valueOf(0.10), BigDecimal.valueOf(10.0)),
    SE(TipoEstado.SE, BigDecimal.valueOf(0.14), BigDecimal.valueOf(30.0)),
    TO(TipoEstado.TO, BigDecimal.valueOf(0.15), BigDecimal.valueOf(40.0));

    private final TipoEstado tipoEstado;
    private final BigDecimal taxaImposto;
    private final BigDecimal taxaEntrega;

    TipoTaxaEstado(TipoEstado tipoEstado,
                   BigDecimal taxaImposto,
                   BigDecimal taxaEntrega) {

        this.tipoEstado = tipoEstado;
        this.taxaImposto = taxaImposto;
        this.taxaEntrega = taxaEntrega;
    }

    public String getNomeMaiusculo() {
        return this.tipoEstado.getNomeMaiusculo();
    }

    public static TipoTaxaEstado of(String codigoEstado) {
        return of(TipoEstado.of(codigoEstado));
    }

    public static TipoTaxaEstado of(TipoEstado tipoEstado) {
        return getStreamValues()
                .filter(tipoTaxaEstado -> Objects.equals(tipoTaxaEstado.getTipoEstado(), tipoEstado))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Código do Estado %s inválido e/ou inexistente para TipoTaxaEstado.", tipoEstado)));
    }

    private static Stream<TipoTaxaEstado> getStreamValues() {
        return Stream.of(TipoTaxaEstado.values());
    }
}
