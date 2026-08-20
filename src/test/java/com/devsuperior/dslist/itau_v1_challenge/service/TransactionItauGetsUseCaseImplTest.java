package com.devsuperior.dslist.itau_v1_challenge.service;

import com.devsuperior.dslist.exceptions.TransactionItauNotFoundException;
import com.devsuperior.dslist.itau_v1_challenge.core.ports.TransactionItauMemoryPort;
import com.devsuperior.dslist.itau_v1_challenge.core.usecase.TransactionItauGetsUseCaseImpl;
import com.devsuperior.dslist.itau_v1_challenge.core.usecase.TransactionItauRemoveUseCaseImpl;
import com.devsuperior.dslist.itau_v1_challenge.core.usecase.output.TransactionItauOutput;
import com.devsuperior.dslist.util.factory.itau_challenge.ItauTransactionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionItauGetsUseCaseImplTest {

    @Mock private TransactionItauMemoryPort transactionItauMemoryPort;

    @InjectMocks private TransactionItauGetsUseCaseImpl transactionItauGetsUseCase;

    @InjectMocks private TransactionItauRemoveUseCaseImpl transactionItauRemoveUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar todas transactions como DTOs")
    void returnAllTransactionsAsDTOs() {
        // -- 01_Cenário
        var transaction = ItauTransactionFactory.buildMockTransactionItau();
        when(transactionItauMemoryPort.getAllTransactionsInMemory()).thenReturn(List.of(transaction));

        // -- 02_Ação
        var result = transactionItauGetsUseCase.getAll();

        // -- 03_Verificação_Validação
        assertEquals(1, result.size());
        verify(transactionItauMemoryPort, times(1)).getAllTransactionsInMemory();
    }

    @Test
    @DisplayName("Deve retornar transaction por ID como DTO")
    void returnTransactionByIdAsDTO() {
        // -- 01_Cenário
        var transactionId = UUID.randomUUID().toString();
        var transaction = ItauTransactionFactory.buildMockTransactionItau();
        transaction.setId(transactionId);

        when(transactionItauMemoryPort.getByIdInMemory(transactionId)).thenReturn(Optional.of(transaction));

        // -- 02_Ação
        var result = transactionItauGetsUseCase.getById(transactionId);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(transactionId, result.getId());

        verify(transactionItauMemoryPort, times(1)).getByIdInMemory(transactionId);
    }

    @Test
    @DisplayName("Deve lançar exception não encontrar transaction por ID")
    void throwExceptionWhenTransactionIdNotFound() {
        // -- 01_Cenário
        var transactionId = UUID.randomUUID().toString();

        when(transactionItauMemoryPort.getByIdInMemory(transactionId)).thenReturn(Optional.empty());

        // -- 02_Ação
        var exception = assertThrows(TransactionItauNotFoundException.class, () -> transactionItauGetsUseCase.getById(transactionId));

        // -- 03_Verificação_Validação
        assertEquals(String.format("Nenhuma transação encontrada por meio do id da transação %s.", transactionId), exception.getMessage());

        verify(transactionItauMemoryPort, times(1)).getByIdInMemory(transactionId);
    }

    @Test
    @DisplayName("Deve excluir transaction por ID")
    void deleteTransactionById() {
        // -- 01_Cenário
        var transactionId = UUID.randomUUID().toString();

        doNothing().when(transactionItauMemoryPort).deleteByIdInMemory(transactionId);

        // -- 02_Ação
        assertDoesNotThrow(() -> transactionItauRemoveUseCase.deleteById(transactionId));

        // -- 03_Verificação_Validação
        verify(transactionItauMemoryPort, times(1)).deleteByIdInMemory(transactionId);
    }

    @Test
    @DisplayName("Deve excluir todas as transações existentes")
    void deleteAllTransactions() {
        // -- 01_Cenário
        doNothing().when(transactionItauMemoryPort).deleteAll();

        // -- 02_Ação
        assertDoesNotThrow(() -> transactionItauRemoveUseCase.deleteAll());

        // -- 03_Verificação_Validação
        verify(transactionItauMemoryPort, times(1)).deleteAll();
    }

    @Test
    @DisplayName("Deve retornar as transações dentro do intervalo informado")
    void returnTransactionsWithinRange() {
        // -- 01_Cenário
        final var secondsRange = 60;
        final var dateTimeRange = OffsetDateTime.now().minusSeconds(secondsRange);

        final var transactionItau = ItauTransactionFactory.buildMockTransactionItau();
        transactionItau.setCreatedAt(dateTimeRange.plusSeconds(30).toLocalDateTime());

        when(transactionItauMemoryPort.getTransactionsByDateTimeInMemory(any(OffsetDateTime.class)))
                .thenReturn(List.of(transactionItau));

        // -- 02_Ação
        var result = transactionItauGetsUseCase.getAllTransactionsByRange(secondsRange);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(transactionItauMemoryPort, times(1))
                .getTransactionsByDateTimeInMemory(any(OffsetDateTime.class));
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando nenhuma transação for encontrada no intervalo")
    void throwExceptionWhenNoTransactionsFound() {
        // -- 01_Cenário
        var secondsRange = 60;

        when(transactionItauMemoryPort.getTransactionsByDateTimeInMemory(any(OffsetDateTime.class)))
                .thenReturn(Collections.emptyList());

        // -- 02_Ação
        assertThrows(TransactionItauNotFoundException.class, () -> transactionItauGetsUseCase.getAllTransactionsByRange(secondsRange));

        // -- 03_Verificação_Validação
        verify(transactionItauMemoryPort, times(1))
                .getTransactionsByDateTimeInMemory(any(OffsetDateTime.class));
    }

    @Test
    @DisplayName("Should filter out transactions outside the given range")
    void filterOutTransactionsOutsideRange() {
        // -- 01_Cenário
        var secondsRange = 60;
        var dateTimeRange = OffsetDateTime.now().minusSeconds(secondsRange);

        var transaction1 = ItauTransactionFactory.buildMockTransactionItau();
        transaction1.setCreatedAt(dateTimeRange.minusSeconds(10).toLocalDateTime());

        var transaction2 = ItauTransactionFactory.buildMockTransactionItau();
        transaction2.setCreatedAt(dateTimeRange.plusSeconds(10).toLocalDateTime());

        when(transactionItauMemoryPort.getTransactionsByDateTimeInMemory(any(OffsetDateTime.class)))
                .thenReturn(List.of(transaction1, transaction2));

        // -- 02_Ação
        var result = transactionItauGetsUseCase.getAllTransactionsByRange(secondsRange);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(2, result.size());

        Optional<TransactionItauOutput> optionalDTO = result.stream().findFirst();
        assertNotNull(optionalDTO);
        assertTrue(optionalDTO.isPresent());

        final var transactionItauResponseDTO = optionalDTO.get();
        assertNotNull(transactionItauResponseDTO);

        verify(transactionItauMemoryPort, times(1))
                .getTransactionsByDateTimeInMemory(any(OffsetDateTime.class));
    }

}