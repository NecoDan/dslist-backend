package com.devsuperior.dslist.adapter.out.dto.external.btg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderBtgPactualDTO implements Serializable {

    @Serial private static final long serialVersionUID = 4721674851495359446L;

    @JsonProperty("codigoPedido")
    private UUID orderId;

    @JsonProperty("codigoCliente")
    private UUID customerId;

    @JsonProperty("itens")
    private List<OrderItemBtgPactualDTO> items;
}
