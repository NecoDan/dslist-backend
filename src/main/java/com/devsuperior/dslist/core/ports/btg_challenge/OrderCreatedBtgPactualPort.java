package com.devsuperior.dslist.core.ports.btg_challenge;

import com.devsuperior.dslist.adapter.out.dto.external.btg.OrderBtgPactualDTO;

public interface OrderCreatedBtgPactualPort {
    void producerMessageCreatedOrder(OrderBtgPactualDTO payload);
}
