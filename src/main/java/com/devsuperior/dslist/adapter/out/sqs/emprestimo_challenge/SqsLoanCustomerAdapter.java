package com.devsuperior.dslist.adapter.out.sqs.emprestimo_challenge;

import com.devsuperior.dslist.adapter.out.dto.external.emprestimos.CustomerLoanSqsDTO;
import com.devsuperior.dslist.exceptions.JsonProcessingMapperException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqsLoanCustomerAdapter implements LoanCustomerSqsPort {

    @Value("${fila.producer.name.sqs-solicitar-regitro-movimento-cliente}")
    private String urlSql;

    private final SqsTemplate sqsTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void produzirMensagem(CustomerLoanSqsDTO customerLoanSqsDTO) {
        try {
            var payload = objectMapper.writeValueAsString(customerLoanSqsDTO);
            sqsTemplate.send(to -> to.queue(urlSql).payload(payload));
            log.info("Mensagem enviada com sucesso para a fila SQS: {}", payload);
        } catch (JsonProcessingException e) {
            log.error("Erro ao enviar mensagem para a fila SQS: {}. Na tentativa de serializar o objeto CustomerLoanSqsDTO para JSON: {}",
                    urlSql, e.getMessage()
            );
            throw new JsonProcessingMapperException(
                    "Erro ao enviar mensagem para a fila SQS: %s. Na tentativa de serializar o objeto CustomerLoanSqsDTO para JSON:"
                            .formatted(urlSql), e
            );
        }
    }
}
