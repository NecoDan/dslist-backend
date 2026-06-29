package com.devsuperior.dslist.emprestimos_challenge.domain;


import com.devsuperior.dslist.emprestimos_challenge.util.LoanChallengeFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    @Test
    void shouldReturnTrueWhenIncomeIsLowerThanValue() {
        final var customer = LoanChallengeFactory.createCustomerWithIncome(BigDecimal.valueOf(1500.00));
        BigDecimal valueToCompare = new BigDecimal("2000.00");

        assertThat(customer.isIncomeEqualOrLowerThan(valueToCompare))
                .isTrue();
    }

    @Test
    void shouldReturnFalseWhenIncomeIsEqualToValue() {
        final var customer = LoanChallengeFactory.createCustomerWithIncome(BigDecimal.valueOf(2000.00));
        BigDecimal valueToCompare = new BigDecimal("2000.00");

        assertThat(customer.isIncomeEqualOrLowerThan(valueToCompare))
                .isFalse();
    }

    @Test
    void shouldReturnFalseWhenIncomeIsGreaterThanValue() {
        final var customer = LoanChallengeFactory.createCustomerWithIncome(BigDecimal.valueOf(2500.00));
        BigDecimal valueToCompare = new BigDecimal("2000.00");

        assertThat(customer.isIncomeEqualOrLowerThan(valueToCompare))
                .isFalse();
    }

    @Test
    void shouldReturnFalseWhenParamValueIsNull() {
        final var customer = LoanChallengeFactory.createCustomerWithIncome(BigDecimal.valueOf(2000.00));
        assertThat(customer.isIncomeEqualOrLowerThan(null))
                .isFalse();
    }

    @Test
    void shouldReturnFalseWhenCustomerIncomeIsNull() {
        final var customer = LoanChallengeFactory.createCustomerWithIncome(null);
        BigDecimal valueToCompare = new BigDecimal("2000.00");

        assertThat(customer.isIncomeEqualOrLowerThan(valueToCompare)).isFalse();
    }

    @Test
    void shouldReturnTrueWhenIncomeIsExactlyMinValue() {
        final var customer = LoanChallengeFactory.createCustomerWithIncome(BigDecimal.valueOf(1000.00));
        BigDecimal min = new BigDecimal("1000.00");
        BigDecimal max = new BigDecimal("2000.00");

        assertThat(customer.isIncomeBetween(min, max)).isTrue();
    }

    @Test
    void shouldReturnTrueWhenIncomeIsBetweenMinAndMax() {
        final var customer = LoanChallengeFactory.createCustomerWithIncome(BigDecimal.valueOf(1500.00));
        BigDecimal min = new BigDecimal("1000.00");
        BigDecimal max = new BigDecimal("2000.00");

        assertThat(customer.isIncomeBetween(min, max)).isTrue();
    }

    @Test
    void shouldReturnFalseWhenIncomeIsExactlyMaxValue() {
        // O método usa '< max', então o limite superior é exclusivo
        final var customer = LoanChallengeFactory.createCustomerWithIncome(BigDecimal.valueOf(2000.00));
        BigDecimal min = new BigDecimal("1000.00");
        BigDecimal max = new BigDecimal("2000.00");

        assertThat(customer.isIncomeBetween(min, max)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenIncomeIsBelowMin() {
        final var customer = LoanChallengeFactory.createCustomerWithIncome(BigDecimal.valueOf(500.00));
        BigDecimal min = new BigDecimal("1000.00");
        BigDecimal max = new BigDecimal("2000.00");

        assertThat(customer.isIncomeBetween(min, max)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenMinOrMaxValuesAreNull() {
        final var customer = LoanChallengeFactory.createCustomerWithIncome(BigDecimal.valueOf(1500.00));
        BigDecimal validValue = new BigDecimal("1000.00");

        assertThat(customer.isIncomeBetween(null, validValue))
                .isFalse();

        assertThat(customer.isIncomeBetween(validValue, null))
                .isFalse();
    }

    @Test
    void shouldReturnFalseWhenCustomerIncomeIsNullV2() {
        final var customer = LoanChallengeFactory.createCustomerWithIncome(null);
        BigDecimal min = new BigDecimal("1000.00");
        BigDecimal max = new BigDecimal("2000.00");

        assertThat(customer.isIncomeBetween(min, max)).isFalse();
    }


    @Test
    void shouldReturnTrueWhenCustomerAgeIsLower() {
        final var customer = LoanChallengeFactory.createCustomerWithAge(25);
        assertThat(customer.isAgeLowerThan(30)).isTrue();
    }

    @Test
    void shouldReturnFalseWhenCustomerAgeIsEqual() {
        final var customer = LoanChallengeFactory.createCustomerWithAge(30);
        assertThat(customer.isAgeLowerThan(30)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenCustomerAgeIsGreater() {
        final var customer = LoanChallengeFactory.createCustomerWithAge(35);
        assertThat(customer.isAgeLowerThan(30)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenParamAgeIsNull() {
        final var customer = LoanChallengeFactory.createCustomerWithAge(25);
        assertThat(customer.isAgeLowerThan(null)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenCustomerAgeIsNull() {
        final var customer = LoanChallengeFactory.createCustomerWithAge(null);
        assertThat(customer.isAgeLowerThan(30)).isFalse();
    }

    @Test
    void shouldReturnTrueWhenLocationMatchesExactly() {
        final var customer = LoanChallengeFactory.createCustomerWithLocation("SP");
        assertThat(customer.isFromLocation("SP")).isTrue();
    }

    @Test
    void shouldReturnTrueWhenLocationMatchesIgnoringCase() {
        final var customer = LoanChallengeFactory.createCustomerWithLocation("rj");
        assertThat(customer.isFromLocation("RJ")).isTrue();
    }

    @Test
    void shouldReturnFalseWhenLocationDoesNotMatch() {
        final var customer = LoanChallengeFactory.createCustomerWithLocation("MG");
        assertThat(customer.isFromLocation("BA")).isFalse();
    }

    @Test
    void shouldReturnFalseWhenCustomerLocationIsNull() {
        final var customer = LoanChallengeFactory.createCustomerWithLocation(null);
        assertThat(customer.isFromLocation("SP")).isFalse();
    }

    @Test
    void shouldReturnFalseWhenParamLocationIsNull() {
        final var customer = LoanChallengeFactory.createCustomerWithLocation("SP");
        assertThat(customer.isFromLocation(null)).isFalse();
    }


}