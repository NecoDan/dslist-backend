package com.devsuperior.dslist.emprestimos_challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {

    private Integer age;
    private String cpf;
    private String name;
    private BigDecimal income;
    private String location;

    private boolean isIncomeValid() {
        return Objects.nonNull(this.income);
    }

    public boolean isIncomeEqualOrLowerThan(BigDecimal value) {
        return Objects.nonNull(value) && isIncomeValid() && income.compareTo(value) < 0;
    }

    public boolean isIncomeBetween(BigDecimal minValue, BigDecimal maxValue) {
        return isIncomeValid()
                && Objects.nonNull(minValue)
                && Objects.nonNull(maxValue)
                && income.compareTo(minValue) >= 0
                && income.compareTo(maxValue) < 0;
    }

    public boolean isAgeLowerThan(Integer age) {
        return Objects.nonNull(age)
                && Objects.nonNull(this.age)
                && this.age < age;
    }

    public boolean isFromLocation(String locationParam) {
        return Objects.nonNull(this.location) && this.location.equalsIgnoreCase(locationParam);
    }
}
