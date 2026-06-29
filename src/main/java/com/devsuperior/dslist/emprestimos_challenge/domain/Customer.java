package com.devsuperior.dslist.emprestimos_challenge.domain;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customer implements Serializable {

    @Serial private static final long serialVersionUID = -4575723883575630710L;

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
                && isAgeValid()
                && this.age < age;
    }

    private boolean isAgeValid() {
        return Objects.nonNull(this.age) && this.age > 0;
    }

    public boolean isFromLocation(String locationParam) {
        return StringUtils.isNoneEmpty(locationParam)
                && StringUtils.isNoneEmpty(this.location)
                && this.location.equalsIgnoreCase(locationParam);
    }
}
