package com.devsuperior.dslist.itau_v1_challenge.biz;

import com.devsuperior.dslist.exceptions.TransactionItauNotFoundException;
import com.devsuperior.dslist.itau_v1_challenge.adapter.out.database.TransactionItauMemoryAdapter;
import com.devsuperior.dslist.itau_v1_challenge.core.domain.TransactionItau;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionItauMemoryAdapterTest {

    @Test
    void createTransactionInMemoryShouldAddTransactionToList() {
        // -- 01_Cenário
        final var transactionItauBusiness = new TransactionItauMemoryAdapter();
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
        final var transactionItauBusiness = new TransactionItauMemoryAdapter();
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
        final var transactionItauBusiness = new TransactionItauMemoryAdapter();

        // -- 02_Ação
        var result = transactionItauBusiness.getByIdInMemory(UUID.randomUUID().toString());

        // -- 03_Verificação_Validação
        assertTrue(result.isEmpty());
    }

    @Test
    void deleteByIdInMemoryShouldRemoveTransactionWhenIdExists() {
        // -- 01_Cenário
        final var transactionItauBusiness = new TransactionItauMemoryAdapter();
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
        TransactionItauMemoryAdapter business = new TransactionItauMemoryAdapter();

        assertThrows(TransactionItauNotFoundException.class,
                () -> business.deleteByIdInMemory(UUID.randomUUID().toString())
        );
    }

    @Test
    void getTransactionsByDateTimeInMemoryShouldReturnTransactionsWithinDateRange() {
        // -- 01_Cenário
        final var transactionItauBusiness = new TransactionItauMemoryAdapter();

        final var transaction1 = new TransactionItau();
        transaction1.setCreatedAt(OffsetDateTime.now().minusDays(1).toLocalDateTime());

        final var transaction2 = new TransactionItau();
        transaction2.setCreatedAt(OffsetDateTime.now().minusHours(1).toLocalDateTime());
        transactionItauBusiness.createTransactionInMemory(transaction1);
        transactionItauBusiness.createTransactionInMemory(transaction2);

        final var filtroOffsetDateTime = OffsetDateTime.now().minusDays(2);

        // -- 02_Ação
        var result = transactionItauBusiness.getTransactionsByDateTimeInMemory(filtroOffsetDateTime);

        // -- 03_Verificação_Validação
        assertEquals(2, result.size());
    }

    @Test
    void getTransactionsByDateTimeInMemoryShouldReturnEmptyWhenNoTransactionsInRange() {
        // -- 01_Cenário
        final var transactionItauBusiness = new TransactionItauMemoryAdapter();

        final var transaction = new TransactionItau();
        final var dtCriacao = OffsetDateTime.now().minusDays(3).toLocalDateTime();

        transaction.setCreatedAt(dtCriacao);
        transactionItauBusiness.createTransactionInMemory(transaction);

        final var filtroOffsetDateTime = OffsetDateTime.now().minusDays(1);

        // -- 02_Ação
        var result = transactionItauBusiness.getTransactionsByDateTimeInMemory(filtroOffsetDateTime);

        // -- 03_Verificação_Validação
        assertTrue(result.isEmpty());
    }

    @Test
    void getTransactionsByDateTimeInMemoryShouldReturnEmptyWhenNoTransactionsExist() {
        // -- 01_Cenário
        final var transactionItauBusiness = new TransactionItauMemoryAdapter();

        // -- 02_Ação
        var result = transactionItauBusiness.getTransactionsByDateTimeInMemory(OffsetDateTime.now());

        // -- 03_Verificação_Validação
        assertTrue(result.isEmpty());
    }
}