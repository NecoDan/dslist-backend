package com.devsuperior.dslist.picpay_challenge.service;

import com.devsuperior.dslist.picpay_challenge.domain.Transaction;
import com.devsuperior.dslist.picpay_challenge.ports.TransactionPicPayPort;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    @Mock
    private TransactionPicPayPort transactionPicPayPort;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void getAll() {
        // -- 01_Cenário
        final var transactionVar1 = mock(Transaction.class);
        final var transactionVar2 = mock(Transaction.class);

        when(transactionPicPayPort.getAllTransactions())
                .thenReturn(Arrays.asList(transactionVar1, transactionVar2));

        // -- 02_Ação
        final var listResult = transactionService.getAll();

        // -- 03_Verificação_Validação
        assertNotNull(listResult);
        assertTrue(CollectionUtils.isNotEmpty(listResult));
        assertEquals(2, listResult.size());
        assertNotNull(listResult.get(0));
        assertNotNull(listResult.get(1));
        assertInstanceOf(Transaction.class, listResult.get(0));
        assertInstanceOf(Transaction.class, listResult.get(1));
    }

    @Test
    void getAllWithForEach() {
    }

    @Test
    void createTransaction() {
    }

    @Test
    void getById() {
    }
}