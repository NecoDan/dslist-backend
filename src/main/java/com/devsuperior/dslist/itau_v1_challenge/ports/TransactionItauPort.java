package com.devsuperior.dslist.itau_v1_challenge.ports;

import com.devsuperior.dslist.itau_v1_challenge.domain.TransactionItau;

import java.util.List;
import java.util.Optional;

public interface TransactionItauPort {
    List<TransactionItau> getAllTransactionsInMemory();

    TransactionItau createTransactionInMemory(TransactionItau transactionItau);

    Optional<TransactionItau> getByIdInMemory(String transactionId);

    void deleteByIdInMemory(final String transactionId);

    void deleteAll();
}
