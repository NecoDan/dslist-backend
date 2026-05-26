package com.devsuperior.dslist.itau_v1_challenge.biz;

import com.devsuperior.dslist.exceptions.TransactionItauCreateFailedException;
import com.devsuperior.dslist.exceptions.TransactionItauNotFoundException;
import com.devsuperior.dslist.itau_v1_challenge.domain.TransactionItau;
import com.devsuperior.dslist.itau_v1_challenge.ports.TransactionItauPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransactionItauBusiness implements TransactionItauPort {

    private final List<TransactionItau> transactionItauList = new ArrayList<>();

    @Override
    public List<TransactionItau> getAllTransactionsInMemory() {
        return this.transactionItauList;
    }

    @Override
    public TransactionItau createTransactionInMemory(TransactionItau transactionItau) {
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
        return this.transactionItauList.stream()
                .filter(transactionItau -> transactionId.equals(transactionItau.getId()))
                .findFirst();
    }

    @Override
    public void deleteByIdInMemory(String transactionId) {
        getByIdInMemory(transactionId)
                .orElseThrow(() ->
                        new TransactionItauNotFoundException("Nenhuma transação encontrada por meio do id da transação %s.".formatted(transactionId))
                );

        this.transactionItauList.removeIf(transactionItau -> transactionId.equals(transactionItau.getId()));
    }
}
