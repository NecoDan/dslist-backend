package com.devsuperior.dslist.core.usecase.btg_challenge;

import com.devsuperior.dslist.core.mappers.OrderBtgPactualMapper;
import com.devsuperior.dslist.core.ports.btg_challenge.OrderCreatedBtgPactualPort;
import com.devsuperior.dslist.core.usecase.btg_challenge.input.OrderBtgPactualInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderCreatedBtgPactualUseCaseImpl implements OrderCreatedBtgPactualUseCase {

    private final OrderCreatedBtgPactualPort orderCreatedBtgPactualPort;
    private final OrderBtgPactualMapper orderBtgPactualMapper;

    @Override
    public void producerMessageCreatedOrder(OrderBtgPactualInput input) {
        log.info("BTG_PACTUAL_CHALLENGE - Iniciando processamento");

        final var orderBtgPactualDTO = orderBtgPactualMapper.toOrderBtgPactualDTO(input);
        orderCreatedBtgPactualPort.producerMessageCreatedOrder(orderBtgPactualDTO);

        log.info("BTG_PACTUAL_CHALLENGE - Message enviada c/ sucesso");
    }
}
