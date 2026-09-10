package com.devsuperior.dslist.adapter.in.http.controllers.events;

import com.devsuperior.dslist.core.usecase.btg_challenge.OrderCreatedBtgPactualUseCase;
import com.devsuperior.dslist.core.usecase.btg_challenge.input.OrderBtgPactualInput;
import com.devsuperior.dslist.utils.logs.MdcUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/events")
@Slf4j
public class EventProducerMessageController {

    private final OrderCreatedBtgPactualUseCase orderCreatedBtgPactualUseCase;

    @PostMapping(value = "/btg-pactual/orders")
    @Operation(summary = "Gerar mensagem criação Pedido", description = "Gerar uma solicitacao para criação de um " +
            "novo pedido via mensagem com os dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitacao criada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> producerMessageOrderBtgPactual(@Valid @RequestBody OrderBtgPactualInput input) {
        try {
            MdcUtils.putTransactionIdRandom();
            log.info("BTG_PACTUAL_CHALLENGE - Inicializando evento para criação do Pedido via RabbitMQ");

            orderCreatedBtgPactualUseCase.producerMessageCreatedOrder(input);
            return ResponseEntity.ok("Mensagem para criação do pedido Btg Pactual criada com sucesso");
        } finally {
            MdcUtils.clear();
        }
    }
}
