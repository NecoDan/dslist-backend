package com.devsuperior.dslist.itau_v1_challenge.service;

import com.devsuperior.dslist.exceptions.EntityCreateFailedException;
import com.devsuperior.dslist.exceptions.TransactionItauNotFoundException;
import com.devsuperior.dslist.itau_v1_challenge.domain.TransactionItau;
import com.devsuperior.dslist.itau_v1_challenge.dto.internal.TransactionItauResponseDTO;
import com.devsuperior.dslist.itau_v1_challenge.dto.request.TransactionItauRequestDTO;
import com.devsuperior.dslist.itau_v1_challenge.ports.TransactionItauPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionItauService {

    private final TransactionItauPort transactionItauPort;

    public List<TransactionItauResponseDTO> getAll() {
        return transactionItauPort.getAllTransactionsInMemory()
                .stream()
                .map(TransactionItauResponseDTO::new)
                .toList();
    }

    public TransactionItauResponseDTO createTransaction(TransactionItauRequestDTO transactionDTO) {
        log.info("Inicializando processamento de criação de transação. Payload: {}", transactionDTO);

        if (transactionDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("O valor da transação deve ser maior que zero. Payload: {}", transactionDTO);
            throw new EntityCreateFailedException("O valor da transação deve ser maior que zero.");
        }

        if (isCreatedAtInvalid(transactionDTO)) {
            log.error("A data de criação da transação é obrigatória. Data e hora maiores que o momento atual não são permitidos. Payload: {}", transactionDTO);
            throw new EntityCreateFailedException("A data de criação da transação é obrigatória. Data e hora maiores que o momento atual não são permitidos.");
        }

        return new TransactionItauResponseDTO(
                transactionItauPort.createTransactionInMemory(
                        new TransactionItau(transactionDTO)
                )
        );
    }

    private boolean isCreatedAtInvalid(TransactionItauRequestDTO transactionDTO) {
        return transactionDTO.getCreatedAt() == null
                || transactionDTO.getCreatedAt().isAfter(OffsetDateTime.now());
    }

    public TransactionItauResponseDTO getById(final String transactionId) {
        return new TransactionItauResponseDTO(
                transactionItauPort.getByIdInMemory(transactionId)
                        .orElseThrow(() ->
                                new TransactionItauNotFoundException(
                                        "Nenhuma transação encontrada por meio do id da transação %s.".formatted(transactionId)
                                )
                        )
        );
    }

    public void deleteById(final String transactionId) {
        transactionItauPort.deleteByIdInMemory(transactionId);
    }

    public void deleteAll() {
        transactionItauPort.deleteAll();
    }
}
