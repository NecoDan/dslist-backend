package com.devsuperior.dslist.itau_v1_challenge.biz;

import com.devsuperior.dslist.exceptions.TransactionItauCreateFailedException;
import com.devsuperior.dslist.exceptions.TransactionItauNotFoundException;
import com.devsuperior.dslist.itau_v1_challenge.domain.TransactionItau;
import com.devsuperior.dslist.itau_v1_challenge.ports.TransactionItauPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionItauBusiness implements TransactionItauPort {

    private final List<TransactionItau> transactionItauList = new ArrayList<>();

    @Override
    public List<TransactionItau> getAllTransactionsInMemory() {
        log.info("Buscando todas as transações existentes.");
        return this.transactionItauList;
    }

    @Override
    public List<TransactionItau> getTransactionsByDateTimeInMemory(OffsetDateTime dateTimeRange) {
        log.info("Buscandos transações existentes criadas no intervalo de {}", dateTimeRange);

        return this.getAllTransactionsInMemory()
                .stream()
                .filter(transactionItau -> transactionItau.getCreatedAt().isAfter(dateTimeRange.toLocalDateTime()))
                .toList();
    }

    @Override
    public TransactionItau createTransactionInMemory(TransactionItau transactionItau) {
        log.info("Criandoa um transação. Payload: {}", transactionItau);

        final var transactionId = UUID.randomUUID().toString();
        transactionItau.setId(transactionId);
        this.transactionItauList.add(transactionItau);

        return getByIdInMemory(transactionId)
                .orElseThrow(
                        () -> new TransactionItauCreateFailedException("Falha ao criar a transação.")
                );
    }

    @Override
    public Optional<TransactionItau> getByIdInMemory(final String transactionId) {
        log.info("Buscando transação por meio do id da transação {}.", transactionId);

        return this.transactionItauList.stream()
                .filter(transactionItau -> transactionId.equals(transactionItau.getId()))
                .findFirst();
    }

    @Override
    public void deleteByIdInMemory(String transactionId) {
        log.info("Excluindo transação por meio do id da transação {}.", transactionId);

        getByIdInMemory(transactionId)
                .orElseThrow(() ->
                        new TransactionItauNotFoundException("Nenhuma transação encontrada por meio do id da transação %s.".formatted(transactionId))
                );

        this.transactionItauList.removeIf(transactionItau -> transactionId.equals(transactionItau.getId()));
    }

    @Override
    public void deleteAll() {
        log.info("Excluindo todas as transações existentes.");

        if (CollectionUtils.isEmpty(this.transactionItauList)) {
            throw new TransactionItauNotFoundException("Nenhuma transação encontrada para ser deletada.");
        }

        this.transactionItauList.clear();
    }
}
