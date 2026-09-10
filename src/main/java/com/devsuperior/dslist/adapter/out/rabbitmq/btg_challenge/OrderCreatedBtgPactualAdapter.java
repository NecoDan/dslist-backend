package com.devsuperior.dslist.adapter.out.rabbitmq.btg_challenge;

import com.devsuperior.dslist.adapter.out.dto.external.btg.OrderBtgPactualDTO;
import com.devsuperior.dslist.core.ports.btg_challenge.OrderCreatedBtgPactualPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreatedBtgPactualAdapter implements OrderCreatedBtgPactualPort {

    private final BtgPactualOrderProducer btgPactualOrderProducer;

    @Override
    public void producerMessageCreatedOrder(OrderBtgPactualDTO payload) {
        log.info("BTG_PACTUAL_CHALLENGE - Iniciando processamento do envio da mensagem via producer RabbitMQ:");
        btgPactualOrderProducer.sendMessage(payload);
    }
}
