package com.devsuperior.dslist.taxes.services.discount;


import com.devsuperior.dslist.utils.FunctionalUtils;
import org.apache.commons.lang3.RandomUtils;

public class CalculaPercentual {

    public static void main(String[] args) {

        final double valor = RandomUtils.secure().randomDouble(100.0D, 1000.0D);

        CalculoPercentual calculoPercentual10 = new CalculoDezPorCento();
        calculoPercentual10.calling();
        final double porcentagemVar2 = calculoPercentual10.calcular(valor);
        calculoPercentual10.data();

        double valorDesconto = valor - porcentagemVar2;
        imprimir(valor, "10", valorDesconto);

        CalculoPercentual calculoPercentual20 = new CalculoVintePorCento();
        calculoPercentual20.calling();
        final double porcentagemVar1 = calculoPercentual20.calcular(valor);
        calculoPercentual20.data();

        valorDesconto = valor - porcentagemVar1;
        imprimir(valor, "20", valorDesconto);
    }

    private static void imprimir(final double valorProduto,
                                 final String valorMensagemDesconto,
                                 final double valorFinalCalculado) {

        final String var10 = String.format("\nValor Produto %s com desconto de %s%s = %s",
                FunctionalUtils.formatDecimalNumberBy(valorProduto),
                valorMensagemDesconto,
                "%",
                FunctionalUtils.formatDecimalNumberBy(valorFinalCalculado));

        System.out.println(var10);
    }
}
