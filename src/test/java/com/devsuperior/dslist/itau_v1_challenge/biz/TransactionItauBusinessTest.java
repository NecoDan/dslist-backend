package com.devsuperior.dslist.itau_v1_challenge.biz;

import com.devsuperior.dslist.exceptions.TransactionItauNotFoundException;
import com.devsuperior.dslist.itau_v1_challenge.domain.TransactionItau;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionItauBusinessTest {

    @Test
    void createTransactionInMemoryShouldAddTransactionToList() {
        // -- 01_Cenário
        final var transactionItauBusiness = new TransactionItauBusiness();
        final var transactionItau = new TransactionItau();

        // -- 02_Ação
        final var createdTransaction = transactionItauBusiness.createTransactionInMemory(transactionItau);

        // -- 03_Verificação_Validação
        assertNotNull(createdTransaction.getId());
        assertEquals(1, transactionItauBusiness.getAllTransactionsInMemory().size());
    }

    @Test
    void getByIdInMemoryShouldReturnTransactionWhenIdExists() {
        // -- 01_Cenário
        final var transactionItauBusiness = new TransactionItauBusiness();
        final var transactionItau = new TransactionItau();
        final var createdTransactionExample = transactionItauBusiness.createTransactionInMemory(transactionItau);

        // -- 02_Ação
        var result = transactionItauBusiness.getByIdInMemory(createdTransactionExample.getId());

        // -- 03_Verificação_Validação
        assertTrue(result.isPresent());
        assertEquals(createdTransactionExample.getId(), result.get().getId());
    }

    @Test
    void getByIdInMemoryShouldReturnEmptyWhenIdDoesNotExist() {
        // -- 01_Cenário
        final var transactionItauBusiness = new TransactionItauBusiness();

        // -- 02_Ação
        var result = transactionItauBusiness.getByIdInMemory(UUID.randomUUID().toString());

        // -- 03_Verificação_Validação
        assertTrue(result.isEmpty());
    }

    @Test
    void deleteByIdInMemoryShouldRemoveTransactionWhenIdExists() {
        // -- 01_Cenário
        final var transactionItauBusiness = new TransactionItauBusiness();
        final var transactionItau = new TransactionItau();
        final var createdTransactionItauExample = transactionItauBusiness.createTransactionInMemory(transactionItau);

        // -- 02_Ação
        transactionItauBusiness.deleteByIdInMemory(createdTransactionItauExample.getId());

        // -- 03_Verificação_Validação
        assertTrue(transactionItauBusiness.getByIdInMemory(createdTransactionItauExample.getId()).isEmpty());
        assertEquals(0, transactionItauBusiness.getAllTransactionsInMemory().size());
    }

    @Test
    void deleteByIdInMemoryShouldThrowExceptionWhenIdDoesNotExist() {
        TransactionItauBusiness business = new TransactionItauBusiness();

        assertThrows(TransactionItauNotFoundException.class,
                () -> business.deleteByIdInMemory(UUID.randomUUID().toString())
        );
    }
}