package com.devsuperior.dslist.itau_v1_challenge.biz;

import com.devsuperior.dslist.itau_v1_challenge.domain.TransactionItau;
import com.devsuperior.dslist.itau_v1_challenge.ports.TransactionItauPort;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class TransactionItauBusiness implements TransactionItauPort {

    private List<TransactionItau> transactionItauList;

    @Override
    public List<TransactionItau> getAllTransactionsInMemory() {
        inicializeTransactionList();
        return this.transactionItauList;
    }

    @Override
    public TransactionItau createTransactionInMemory(TransactionItau transactionItau) {
        inicializeTransactionList();

        final var transactionId = UUID.randomUUID().toString();
        transactionItau.setId(transactionId);
        this.transactionItauList.add(transactionItau);

        return getByIdInMemory(transactionId)
                .orElseThrow(
                        () -> new IllegalStateException("Falha ao criar uma nova transação.")
                );
    }

    @Override
    public Optional<TransactionItau> getByIdInMemory(final String transactionId) {
        inicializeTransactionList();

        return this.transactionItauList.stream()
                .filter(transactionItau -> StringUtils.equals(transactionId, transactionItau.getId()))
                .findFirst();
    }

    @Override
    public void deleteById(String transactionId) {
        
    }

    private void inicializeTransactionList() {
        if (Objects.isNull(this.transactionItauList)) this.transactionItauList = new ArrayList<>();
    }
}
