package com.devsuperior.dslist.itau_v1_challenge.dto.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serial;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record StatisticsItauResponseDTO(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max

) implements Serializable {
    @Serial private static final long serialVersionUID = -3756219554689400144L;
}
