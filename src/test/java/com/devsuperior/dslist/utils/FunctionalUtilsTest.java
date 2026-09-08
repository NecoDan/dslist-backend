package com.devsuperior.dslist.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FunctionalUtilsTest {

    @Test
    void testFormatCreationDateWithNullInput() {
        // Test when the input is null
        String result = FunctionalUtils.formatCreationDate(null);
        assertEquals(StringUtils.EMPTY, result, "O resultado deve ser uma string vazia quando a entrada for nula");
    }

    @Test
    void testFormatCreationDateWithValidInput() {
        // Test when the input is a valid LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 1, 12, 30, 45);
        String expected = FunctionalUtils.formatCreationDateBy(dateTime); // Assuming this method is implemented
        String result = FunctionalUtils.formatCreationDate(dateTime);
        assertEquals(expected, result, "O resultado deve corresponder à sequência de data formatada para uma entrada válida");
    }

    @Test
    void testFormatDecimalNumberWithValidNumber() {
        BigDecimal input = new BigDecimal("1234.567");
        String result = FunctionalUtils.formatDecimalNumber(input);
        assertEquals("1234.57", result, "O número deve ser arredondado para 2 casas decimais.");
    }

    @Test
    void testFormatDecimalNumberWithExactTwoDecimalPlaces() {
        BigDecimal input = new BigDecimal("1234.56");
        String result = FunctionalUtils.formatDecimalNumber(input);
        assertEquals("1234.56", result, "O número deve permanecer o mesmo se já tiver 2 casas decimais.");
    }

    @Test
    void testFormatDecimalNumberWithZero() {
        BigDecimal input = BigDecimal.ZERO;
        String result = FunctionalUtils.formatDecimalNumber(input);
        assertEquals("0.00", result, "Zero deve ser formatado como 0,00.");
    }

    @Test
    void testFormatDecimalNumberWithNegativeNumber() {
        BigDecimal input = new BigDecimal("-1234.567");
        String result = FunctionalUtils.formatDecimalNumber(input);
        assertEquals("-1234.57", result, "Números negativos devem ser arredondados para 2 casas decimais.");
    }

    @Test
    void testFormatDecimalNumberWithLargeNumber() {
        BigDecimal input = new BigDecimal("1234567890.12345");
        String result = FunctionalUtils.formatDecimalNumber(input);
        assertEquals("1234567890.12", result, "Números grandes devem ser arredondados para 2 casas decimais.");
    }

    @Test
    void testFormatDecimalNumberWithSmallNumber() {
        BigDecimal input = new BigDecimal("0.004");
        String result = FunctionalUtils.formatDecimalNumber(input);
        assertEquals("0.00", result, "Números pequenos menores que 0,005 devem ser arredondados para baixo, para 0,00.");
    }

    @Test
    void testFormatDecimalNumberWithRoundingUp() {
        BigDecimal input = new BigDecimal("1.005");
        String result = FunctionalUtils.formatDecimalNumber(input);
        assertEquals("1.01", result, "Os números devem ser arredondados corretamente quando a terceira casa decimal for 5 ou maior.");
    }

    @Test
    void testFormatDecimalNumberWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            FunctionalUtils.formatDecimalNumber(null);
        }, "A entrada NULL deve gerar uma IllegalArgumentException.");
    }

    @Test
    void testFormatDecimalNumberWithLocalePT_BR() {
        BigDecimal input = new BigDecimal("1234.567");
        Locale.setDefault(new Locale.Builder().setLanguage("pt").setRegion("BR").build());
        String result = FunctionalUtils.formatDecimalNumber(input);
        assertEquals("1234.57", result, "O número deve ser formatado corretamente com a localidade PT_BR.");
    }

    @Test
    void formatCpfWithValidCpf() {
        String cpf = "12345678909";
        String result = FunctionalUtils.formatCpf(cpf);
        assertEquals("123.456.789-09", result, "The CPF should be formatted correctly.");
    }

    @Test
    void formatCpfWithCpfContainingSpecialCharacters() {
        String cpf = "123.456.789-09";
        String result = FunctionalUtils.formatCpf(cpf);
        assertEquals("123.456.789-09", result, "The CPF should be formatted correctly even if it contains special characters.");
    }

    @Test
    void formatCpfWithCpfContainingSpaces() {
        String cpf = " 123 456 789 09 ";
        String result = FunctionalUtils.formatCpf(cpf);
        assertEquals("123.456.789-09", result, "The CPF should be formatted correctly even if it contains spaces.");
    }

    @Test
    void formatCpfWithEmptyString() {
        String cpf = "";
        String result = FunctionalUtils.formatCpf(cpf);
        assertEquals("", result, "An empty CPF should return an empty string.");
    }

    @Test
    void formatCpfWithNullInput() {
        assertThrows(NullPointerException.class, () -> {
            FunctionalUtils.formatCpf(null);
        }, "A null CPF should throw a NullPointerException.");
    }

    @Test
    void formatCpfWithInvalidLengthCpf() {
        String cpf = "12345";
        String result = FunctionalUtils.formatCpf(cpf);
        assertEquals("12345", result, "An invalid length CPF should return the input as is.");
    }

    @Test
    void formatDecimalNumberByWithValidDouble() {
        Double input = 1234.567;
        String result = FunctionalUtils.formatDecimalNumberBy(input);
        assertEquals("1234.57", result, "The number should be rounded to 2 decimal places.");
    }

    @Test
    void formatDecimalNumberByWithExactTwoDecimalPlaces() {
        Double input = 1234.56;
        String result = FunctionalUtils.formatDecimalNumberBy(input);
        assertEquals("1234.56", result, "The number should remain the same if it already has 2 decimal places.");
    }

    @Test
    void formatDecimalNumberByWithZero() {
        Double input = 0.0;
        String result = FunctionalUtils.formatDecimalNumberBy(input);
        assertEquals("0.00", result, "Zero should be formatted as 0.00.");
    }

    @Test
    void formatDecimalNumberByWithNegativeDouble() {
        Double input = -1234.567;
        String result = FunctionalUtils.formatDecimalNumberBy(input);
        assertEquals("-1234.57", result, "Negative numbers should be rounded to 2 decimal places.");
    }

    @Test
    void formatDecimalNumberByWithSmallDouble() {
        Double input = 0.004;
        String result = FunctionalUtils.formatDecimalNumberBy(input);
        assertEquals("0.00", result, "Small numbers less than 0.005 should be rounded down to 0.00.");
    }

    @Test
    void formatDecimalNumberByWithRoundingUp() {
        Double input = 1.005;
        String result = FunctionalUtils.formatDecimalNumberBy(input);
        assertEquals("1.01", result, "Numbers should be rounded correctly when the third decimal place is 5 or greater.");
    }

    @Test
    void formatDecimalNumberByWithNullInput() {
        assertThrows(NullPointerException.class, () -> {
            FunctionalUtils.formatDecimalNumberBy(null);
        }, "A null input should throw a NullPointerException.");
    }
}