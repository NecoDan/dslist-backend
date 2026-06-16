package com.devsuperior.dslist.itau_v1_challenge.service;

import com.devsuperior.dslist.exceptions.EntityCreateFailedException;
import com.devsuperior.dslist.exceptions.TransactionItauNotFoundException;
import com.devsuperior.dslist.itau_v1_challenge.domain.TransactionItau;
import com.devsuperior.dslist.itau_v1_challenge.dto.internal.TransactionItauResponseDTO;
import com.devsuperior.dslist.itau_v1_challenge.ports.TransactionItauPort;
import com.devsuperior.dslist.util.factory.itau_challenge.ItauTransactionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionItauServiceTest {

    @Mock private TransactionItauPort transactionItauPort;

    @InjectMocks private TransactionItauService transactionItauService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar todas transactions como DTOs")
    void returnAllTransactionsAsDTOs() {
        // -- 01_Cenário
        var transaction = ItauTransactionFactory.buildMockTransactionItau();
        when(transactionItauPort.getAllTransactionsInMemory()).thenReturn(List.of(transaction));

        // -- 02_Ação
        var result = transactionItauService.getAll();

        // -- 03_Verificação_Validação
        assertEquals(1, result.size());
        verify(transactionItauPort, times(1)).getAllTransactionsInMemory();
    }

    @Test
    @DisplayName("Deve criar transaction retorna DTO")
    void createTransactionAndReturnAsDTO() {
        // -- 01_Cenário
        var transactionDTO = ItauTransactionFactory.buildMockTransactionItauRequestDTO();
        var createdTransaction = ItauTransactionFactory.buildMockTransactionItau();
        createdTransaction.setId(UUID.randomUUID().toString());

        when(transactionItauPort.createTransactionInMemory(any(TransactionItau.class))).thenReturn(createdTransaction);

        // -- 02_Ação
        var result = transactionItauService.createTransaction(transactionDTO);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(createdTransaction.getId(), result.getId());

        verify(transactionItauPort, times(1)).createTransactionInMemory(any(TransactionItau.class));
    }

    @Test
    @DisplayName("Deve lançar exception quando o valor da transação for menor ou igual a zero")
    void throwExceptionWhenTransactionAmountIsZeroOrNegative() {
        // -- 01_Cenário
        var transactionDTO = ItauTransactionFactory.buildMockTransactionItauRequestDTO();
        transactionDTO.setAmount(BigDecimal.ZERO);

        // -- 02_Ação
        var exception = assertThrows(EntityCreateFailedException.class, () -> transactionItauService.createTransaction(transactionDTO));

        // -- 03_Verificação_Validação
        assertEquals("O valor da transação deve ser maior que zero.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exception quando a data de criação for nula")
    void throwExceptionWhenTransactionCreatedAtIsNull() {
        // -- 01_Cenário
        var transactionDTO = ItauTransactionFactory.buildMockTransactionItauRequestDTO();
        transactionDTO.setCreatedAt(null);

        // -- 02_Ação
        var exception = assertThrows(EntityCreateFailedException.class, () -> transactionItauService.createTransaction(transactionDTO));

        // -- 03_Verificação_Validação
        assertEquals("A data/hora de criação da transação é obrigatória. Data e hora maiores que data atual não são permitidos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exception quando a data de criação for maior que a data atual")
    void throwExceptionWhenTransactionCreatedAtIsInFuture() {
        // -- 01_Cenário
        var transactionDTO = ItauTransactionFactory.buildMockTransactionItauRequestDTO();
        transactionDTO.setCreatedAt(OffsetDateTime.now().plusDays(1));

        // -- 02_Ação
        var exception = assertThrows(EntityCreateFailedException.class, () -> transactionItauService.createTransaction(transactionDTO));

        // -- 03_Verificação_Validação
        assertEquals("A data/hora de criação da transação é obrigatória. Data e hora maiores que data atual não são permitidos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve criar transação com valor válido e data válida")
    void createTransactionWithValidAmountAndCreatedAt() {
        // -- 01_Cenário
        var transactionDTO = ItauTransactionFactory.buildMockTransactionItauRequestDTO();
        transactionDTO.setAmount(BigDecimal.valueOf(100.00));
        transactionDTO.setCreatedAt(OffsetDateTime.now());

        var createdTransaction = ItauTransactionFactory.buildMockTransactionItau();
        createdTransaction.setId(UUID.randomUUID().toString());

        when(transactionItauPort.createTransactionInMemory(any(TransactionItau.class))).thenReturn(createdTransaction);

        // -- 02_Ação
        var result = transactionItauService.createTransaction(transactionDTO);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(createdTransaction.getId(), result.getId());
        verify(transactionItauPort, times(1)).createTransactionInMemory(any(TransactionItau.class));
    }

    @Test
    @DisplayName("Deve retornar transaction por ID como DTO")
    void returnTransactionByIdAsDTO() {
        // -- 01_Cenário
        var transactionId = UUID.randomUUID().toString();
        var transaction = ItauTransactionFactory.buildMockTransactionItau();
        transaction.setId(transactionId);

        when(transactionItauPort.getByIdInMemory(transactionId)).thenReturn(Optional.of(transaction));

        // -- 02_Ação
        var result = transactionItauService.getById(transactionId);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(transactionId, result.getId());

        verify(transactionItauPort, times(1)).getByIdInMemory(transactionId);
    }

    @Test
    @DisplayName("Deve lançar exception não encontrar transaction por ID")
    void throwExceptionWhenTransactionIdNotFound() {
        // -- 01_Cenário
        var transactionId = UUID.randomUUID().toString();

        when(transactionItauPort.getByIdInMemory(transactionId)).thenReturn(Optional.empty());

        // -- 02_Ação
        var exception = assertThrows(TransactionItauNotFoundException.class, () -> transactionItauService.getById(transactionId));

        // -- 03_Verificação_Validação
        assertEquals(String.format("Nenhuma transação encontrada por meio do id da transação %s.", transactionId), exception.getMessage());

        verify(transactionItauPort, times(1)).getByIdInMemory(transactionId);
    }

    @Test
    @DisplayName("Deve excluir transaction por ID")
    void deleteTransactionById() {
        // -- 01_Cenário
        var transactionId = UUID.randomUUID().toString();

        doNothing().when(transactionItauPort).deleteByIdInMemory(transactionId);

        // -- 02_Ação
        assertDoesNotThrow(() -> transactionItauService.deleteById(transactionId));

        // -- 03_Verificação_Validação
        verify(transactionItauPort, times(1)).deleteByIdInMemory(transactionId);
    }

    @Test
    @DisplayName("Deve excluir todas as transações existentes")
    void deleteAllTransactions() {
        // -- 01_Cenário
        doNothing().when(transactionItauPort).deleteAll();

        // -- 02_Ação
        assertDoesNotThrow(() -> transactionItauService.deleteAll());

        // -- 03_Verificação_Validação
        verify(transactionItauPort, times(1)).deleteAll();
    }

    @Test
    @DisplayName("Deve retornar as transações dentro do intervalo informado")
    void returnTransactionsWithinRange() {
        // -- 01_Cenário
        final var secondsRange = 60;
        final var dateTimeRange = OffsetDateTime.now().minusSeconds(secondsRange);

        final var transactionItau = ItauTransactionFactory.buildMockTransactionItau();
        transactionItau.setCreatedAt(dateTimeRange.plusSeconds(30).toLocalDateTime());

        when(transactionItauPort.getTransactionsByDateTimeInMemory(any(OffsetDateTime.class)))
                .thenReturn(List.of(transactionItau));

        // -- 02_Ação
        var result = transactionItauService.getAllTransactionsByRange(secondsRange);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(transactionItauPort, times(1))
                .getTransactionsByDateTimeInMemory(any(OffsetDateTime.class));
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando nenhuma transação for encontrada no intervalo")
    void throwExceptionWhenNoTransactionsFound() {
        // -- 01_Cenário
        var secondsRange = 60;

        when(transactionItauPort.getTransactionsByDateTimeInMemory(any(OffsetDateTime.class)))
                .thenReturn(Collections.emptyList());

        // -- 02_Ação
        assertThrows(TransactionItauNotFoundException.class, () -> transactionItauService.getAllTransactionsByRange(secondsRange));

        // -- 03_Verificação_Validação
        verify(transactionItauPort, times(1))
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

        when(transactionItauPort.getTransactionsByDateTimeInMemory(any(OffsetDateTime.class)))
                .thenReturn(List.of(transaction1, transaction2));

        // -- 02_Ação
        var result = transactionItauService.getAllTransactionsByRange(secondsRange);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(2, result.size());

        Optional<TransactionItauResponseDTO> optionalDTO = result.stream().findFirst();
        assertNotNull(optionalDTO);
        assertTrue(optionalDTO.isPresent());

        final var transactionItauResponseDTO = optionalDTO.get();
        assertNotNull(transactionItauResponseDTO);

        verify(transactionItauPort, times(1))
                .getTransactionsByDateTimeInMemory(any(OffsetDateTime.class));
    }

}