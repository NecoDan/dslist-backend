package com.devsuperior.dslist.itau_v1_challenge.dto.internal;

import com.devsuperior.dslist.itau_v1_challenge.domain.TransactionItau;
import com.devsuperior.dslist.utils.FunctionalUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionItauResponseDTO implements Serializable {

    @Serial private static final long serialVersionUID = 3330527215775191375L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("valor")
    private String amount;

    @JsonProperty("dataHora")
    private String createdAt;

    public TransactionItauResponseDTO(TransactionItau entity){
        BeanUtils.copyProperties(entity, this);
        this.amount = FunctionalUtils.formatDecimalNumber(entity.getAmount());
        this.createdAt = Objects.isNull(entity.getCreatedAt()) ? StringUtils.EMPTY : FunctionalUtils.formatCreationDate(entity.getCreatedAt());
    }
}
