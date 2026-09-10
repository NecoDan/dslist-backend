package com.devsuperior.dslist.adapter.out.rabbitmq.btg_challenge;

import com.devsuperior.dslist.adapter.out.dto.external.btg.OrderBtgPactualDTO;
import com.devsuperior.dslist.exceptions.OrderBtgPactualProducerMessageFailedException;
import com.devsuperior.dslist.utils.FunctionalUtils;
import com.devsuperior.dslist.utils.logs.MdcUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class BtgPactualOrderProducer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.queue.name}")
    private String queeNameProducer;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(OrderBtgPactualDTO payloadDTO) {

        try {
            MdcUtils.putTransactionIdRandom();
            log.info("BTG_PACTUAL_CHALLENGE - RabbitMQ evento/payload a ser enviado na fila {}.", queeNameProducer);

            final var payloadContent = FunctionalUtils.toStringJsonFrom(payloadDTO);
            log.info("BTG_PACTUAL_CHALLENGE - Payload envio: {}", payloadContent);

            final var message = MessageBuilder.withBody(payloadContent.getBytes(StandardCharsets.UTF_8))
                    .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                    .setHeader("btgpactual", "orders")
                    .setMessageId(UUID.randomUUID().toString())
                    .build();

            rabbitTemplate.send(
                    exchangeName,
                    routingKey,
                    message
            );

            log.info("BTG_PACTUAL_CHALLENGE - RabbitMQ evento/payload enviado com sucesso na fila {}.", queeNameProducer);
        } catch (Exception e) {
            log.error("BTG_PACTUAL_CHALLENGE - RabbitMQ erro inesperado ao produzir evento/payload na fila {}: {}",
                    queeNameProducer, e.getMessage(), e);
            throw new OrderBtgPactualProducerMessageFailedException("Erro inesperado ao processar envio de mensagem via RabbitMQ: " + e.getMessage(), e);
        } finally {
            MdcUtils.clear();
        }
    }
}
