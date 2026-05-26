package com.devsuperior.dslist.picpay_challenge.service;

import com.devsuperior.dslist.picpay_challenge.domain.Transaction;
import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionDTO;
import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionResponseDTO;
import com.devsuperior.dslist.picpay_challenge.dto.request.TransactionRequestDTO;
import com.devsuperior.dslist.picpay_challenge.ports.TransactionPicPayPort;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


class TransactionServiceTest {

    @Mock
    private TransactionPicPayPort transactionPicPayPort;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Transaction getBuildTransaction() {
        return Transaction.builder()
                .id(RandomUtils.secure().randomLong())
                .createdAt(LocalDateTime.now())
                .receiver(User.builder()
                        .id(RandomUtils.secure().randomLong())
                        .balance(BigDecimal.valueOf(RandomUtils.secure().randomDouble()))
                        .build())
                .sender(User.builder()
                        .id(RandomUtils.secure().randomLong())
                        .balance(BigDecimal.valueOf(RandomUtils.secure().randomDouble()))
                        .build())
                .amount(BigDecimal.valueOf(RandomUtils.secure().randomDouble()))
                .build();
    }

    @Test
    void getAll() {
        // -- 01_Cenário
        when(transactionPicPayPort.getAllTransactions())
                .thenReturn(Arrays.asList(getBuildTransaction(), getBuildTransaction()));

        // -- 02_Ação
        final var listResult = transactionService.getAll();

        // -- 03_Verificação_Validação
        assertNotNull(listResult);
        assertFalse(CollectionUtils.isEmpty(listResult));
        assertEquals(2, listResult.size());

        final var optionalfirst = listResult.stream().findFirst();
        assertNotNull(optionalfirst);
        assertFalse(optionalfirst.isEmpty());
        assertNotNull(optionalfirst.get());
        assertInstanceOf(TransactionResponseDTO.class, optionalfirst.get());
    }

    @Test
    void getAllWithForEach() {
        // -- 01_Cenário
        when(transactionPicPayPort.getAllTransactions())
                .thenReturn(Arrays.asList(
                                getBuildTransaction(),
                                getBuildTransaction(),
                                getBuildTransaction()
                        )
                );

        // -- 02_Ação
        final var listResult = transactionService.getAllWithForEach();

        // -- 03_Verificação_Validação
        assertNotNull(listResult);
        assertFalse(CollectionUtils.isEmpty(listResult));
        assertEquals(3, listResult.size());

        final var optionalfirst = listResult.stream().findFirst();
        assertNotNull(optionalfirst);
        assertFalse(optionalfirst.isEmpty());
        assertNotNull(optionalfirst.get());
        assertInstanceOf(TransactionResponseDTO.class, optionalfirst.get());
    }

    @Test
    void createTransactionSuccessfully() throws Exception {
        // -- 01_Cenário
        final var amountValue = BigDecimal.valueOf(100);
        var transaction = getBuildTransaction();
        transaction.setAmount(amountValue);

        final var requestDTO = new TransactionRequestDTO(amountValue,
                transaction.getSender().getId(),
                transaction.getReceiver().getId());

        when(transactionPicPayPort.createTransaction(any(TransactionDTO.class)))
                .thenReturn(transaction);

        // -- 02_Ação
        final var response = transactionService.createTransaction(requestDTO);

        // -- 03_Verificação_Validação
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(transaction.getId(), response.getId());
        assertEquals(transaction.getSender().getId(), response.getSender().getId());
        assertEquals(transaction.getReceiver().getId(), response.getReceiver().getId());
    }

    @Test
    void createTransactionThrowsExceptionWhenPortFails() throws Exception {
        // -- 01_Cenário
        final var requestDTO = new TransactionRequestDTO(BigDecimal.valueOf(100), 1L, 2L);

        when(transactionPicPayPort.createTransaction(any(TransactionDTO.class)))
                .thenThrow(new RuntimeException("Transaction creation failed"));

        // -- 02_Ação & 03_Verificação_Validação
        final var exception = assertThrows(Exception.class,
                () -> transactionService.createTransaction(requestDTO)
        );

        assertEquals("Transaction creation failed", exception.getMessage());
    }

    @Test
    void createTransactionHandlesNullRequest() {
        // -- 01_Cenário & 02_Ação & 03_Verificação_Validação
        assertThrows(NullPointerException.class, () -> transactionService.createTransaction(null));
    }

    @Test
    void getById() {
        // -- 01_Cenário
        final var transactionVar14 = getBuildTransaction();
        final var id = transactionVar14.getId();

        when(transactionPicPayPort.getById(anyLong()))
                .thenReturn(transactionVar14);

        // -- 02_Ação
        final var result = transactionService.getById(id);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertInstanceOf(TransactionResponseDTO.class, result);

        assertNotNull(result.getId());
        assertInstanceOf(Long.class, result.getId());
        assertEquals(id, result.getId());
    }
}