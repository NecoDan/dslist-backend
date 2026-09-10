package com.devsuperior.dslist.core.usecase.btg_challenge;

import com.devsuperior.dslist.core.usecase.btg_challenge.input.OrderBtgPactualInput;

public interface OrderCreatedBtgPactualUseCase {
    void producerMessageCreatedOrder(OrderBtgPactualInput input);
}
