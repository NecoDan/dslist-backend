package com.devsuperior.dslist.emprestimos_challenge.domain;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    @Test
    void shouldReturnTrueWhenIncomeIsLowerThanValue() {
        Customer customer = createCustomerWithIncome(BigDecimal.valueOf(1500.00));
        BigDecimal valueToCompare = new BigDecimal("2000.00");

        assertThat(customer.isIncomeEqualOrLowerThan(valueToCompare))
                .isTrue();
    }

    @Test
    void shouldReturnFalseWhenIncomeIsEqualToValue() {
        Customer customer = createCustomerWithIncome(BigDecimal.valueOf(2000.00));
        BigDecimal valueToCompare = new BigDecimal("2000.00");

        assertThat(customer.isIncomeEqualOrLowerThan(valueToCompare))
                .isFalse();
    }

    @Test
    void shouldReturnFalseWhenIncomeIsGreaterThanValue() {
        Customer customer = createCustomerWithIncome(BigDecimal.valueOf(2500.00));
        BigDecimal valueToCompare = new BigDecimal("2000.00");

        assertThat(customer.isIncomeEqualOrLowerThan(valueToCompare))
                .isFalse();
    }

    @Test
    void shouldReturnFalseWhenParamValueIsNull() {
        Customer customer = createCustomerWithIncome(BigDecimal.valueOf(2000.00));
        assertThat(customer.isIncomeEqualOrLowerThan(null))
                .isFalse();
    }

    @Test
    void shouldReturnFalseWhenCustomerIncomeIsNull() {
        Customer customer = createCustomerWithIncome(null);
        BigDecimal valueToCompare = new BigDecimal("2000.00");

        assertThat(customer.isIncomeEqualOrLowerThan(valueToCompare)).isFalse();
    }

    @Test
    void shouldReturnTrueWhenIncomeIsExactlyMinValue() {
        Customer customer = createCustomerWithIncome(BigDecimal.valueOf(1000.00));
        BigDecimal min = new BigDecimal("1000.00");
        BigDecimal max = new BigDecimal("2000.00");

        assertThat(customer.isIncomeBetween(min, max)).isTrue();
    }

    @Test
    void shouldReturnTrueWhenIncomeIsBetweenMinAndMax() {
        Customer customer = createCustomerWithIncome(BigDecimal.valueOf(1500.00));
        BigDecimal min = new BigDecimal("1000.00");
        BigDecimal max = new BigDecimal("2000.00");

        assertThat(customer.isIncomeBetween(min, max)).isTrue();
    }

    @Test
    void shouldReturnFalseWhenIncomeIsExactlyMaxValue() {
        // O método usa '< max', então o limite superior é exclusivo
        Customer customer = createCustomerWithIncome(BigDecimal.valueOf(2000.00));
        BigDecimal min = new BigDecimal("1000.00");
        BigDecimal max = new BigDecimal("2000.00");

        assertThat(customer.isIncomeBetween(min, max)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenIncomeIsBelowMin() {
        Customer customer = createCustomerWithIncome(BigDecimal.valueOf(500.00));
        BigDecimal min = new BigDecimal("1000.00");
        BigDecimal max = new BigDecimal("2000.00");

        assertThat(customer.isIncomeBetween(min, max)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenMinOrMaxValuesAreNull() {
        Customer customer = createCustomerWithIncome(BigDecimal.valueOf(1500.00));
        BigDecimal validValue = new BigDecimal("1000.00");

        assertThat(customer.isIncomeBetween(null, validValue))
                .isFalse();

        assertThat(customer.isIncomeBetween(validValue, null))
                .isFalse();
    }

    @Test
    void shouldReturnFalseWhenCustomerIncomeIsNullV2() {
        Customer customer = createCustomerWithIncome(null);
        BigDecimal min = new BigDecimal("1000.00");
        BigDecimal max = new BigDecimal("2000.00");

        assertThat(customer.isIncomeBetween(min, max)).isFalse();
    }


    @Test
    void shouldReturnTrueWhenCustomerAgeIsLower() {
        Customer customer = createCustomerWithAge(25);
        assertThat(customer.isAgeLowerThan(30)).isTrue();
    }

    @Test
    void shouldReturnFalseWhenCustomerAgeIsEqual() {
        Customer customer = createCustomerWithAge(30);
        assertThat(customer.isAgeLowerThan(30)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenCustomerAgeIsGreater() {
        Customer customer = createCustomerWithAge(35);
        assertThat(customer.isAgeLowerThan(30)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenParamAgeIsNull() {
        Customer customer = createCustomerWithAge(25);
        assertThat(customer.isAgeLowerThan(null)).isFalse();
    }

    @Test
    void shouldReturnFalseWhenCustomerAgeIsNull() {
        Customer customer = createCustomerWithAge(null);
        assertThat(customer.isAgeLowerThan(30)).isFalse();
    }

    @Test
    void shouldReturnTrueWhenLocationMatchesExactly() {
        Customer customer = createCustomerWithLocation("SP");
        assertThat(customer.isFromLocation("SP")).isTrue();
    }

    @Test
    void shouldReturnTrueWhenLocationMatchesIgnoringCase() {
        Customer customer = createCustomerWithLocation("rj");
        assertThat(customer.isFromLocation("RJ")).isTrue();
    }

    @Test
    void shouldReturnFalseWhenLocationDoesNotMatch() {
        Customer customer = createCustomerWithLocation("MG");
        assertThat(customer.isFromLocation("BA")).isFalse();
    }

    @Test
    void shouldReturnFalseWhenCustomerLocationIsNull() {
        Customer customer = createCustomerWithLocation(null);
        assertThat(customer.isFromLocation("SP")).isFalse();
    }

    @Test
    void shouldReturnFalseWhenParamLocationIsNull() {
        Customer customer = createCustomerWithLocation("SP");
        assertThat(customer.isFromLocation(null)).isFalse();
    }

    private Customer createCustomerWithIncome(BigDecimal income) {
        Customer customer = new Customer();
        customer.setIncome(income);
        return customer;
    }

    private Customer createCustomerWithAge(Integer age) {
        Customer customer = new Customer();
        customer.setAge(age);
        return customer;
    }

    private Customer createCustomerWithLocation(String location) {
        Customer customer = new Customer();
        customer.setLocation(location);
        return customer;
    }
}