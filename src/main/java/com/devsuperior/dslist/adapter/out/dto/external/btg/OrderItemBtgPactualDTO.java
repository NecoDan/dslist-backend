package com.devsuperior.dslist.adapter.out.dto.external.btg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemBtgPactualDTO implements Serializable {

    @Serial private static final long serialVersionUID = -2850529223117632884L;

    @JsonProperty("produto")
    private String product;

    @JsonProperty("quantidade")
    private Integer quantity;

    @JsonProperty("preco")
    private BigDecimal price;
}
