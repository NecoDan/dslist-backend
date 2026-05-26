package com.devsuperior.dslist.itau_v1_challenge.service;

import com.devsuperior.dslist.itau_v1_challenge.domain.TransactionItau;
import com.devsuperior.dslist.itau_v1_challenge.dto.request.TransactionItauRequestDTO;
import com.devsuperior.dslist.itau_v1_challenge.ports.TransactionItauPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionItauServiceTest {

    @Mock
    private TransactionItauPort transactionItauPort;

    @InjectMocks
    private TransactionItauService transactionItauService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private TransactionItau buildMockTransactionItau() {
        return TransactionItau.builder()
                .id(UUID.randomUUID().toString())
                .amount(BigDecimal.valueOf(100.00))
                .createdAt(LocalDateTime.now())
                .build();
    }

    private TransactionItauRequestDTO buildMockTransactionItauRequestDTO() {
        return TransactionItauRequestDTO.builder()
                .amount(BigDecimal.valueOf(100.00))
                .createdAt(LocalDateTime.now().atOffset(java.time.ZoneOffset.UTC))
                .build();
    }

    @Test
    @DisplayName("Deve retornar todas transactions como DTOs")
    void returnAllTransactionsAsDTOs() {
        // -- 01_Cenário
        var transaction = buildMockTransactionItau();
        when(transactionItauPort.getAllTransactionsInMemory())
                .thenReturn(List.of(transaction));

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
        var transactionDTO = buildMockTransactionItauRequestDTO();
        var createdTransaction = buildMockTransactionItau();
        createdTransaction.setId(UUID.randomUUID().toString());

        when(transactionItauPort.createTransactionInMemory(any(TransactionItau.class)))
                .thenReturn(createdTransaction);

        // -- 02_Ação
        var result = transactionItauService.createTransaction(transactionDTO);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(createdTransaction.getId(), result.getId());

        verify(transactionItauPort, times(1))
                .createTransactionInMemory(any(TransactionItau.class));
    }

    @Test
    @DisplayName("Deve retornar transaction por ID como DTO")
    void returnTransactionByIdAsDTO() {
        // -- 01_Cenário
        var transactionId = UUID.randomUUID().toString();
        var transaction = buildMockTransactionItau();
        transaction.setId(transactionId);

        when(transactionItauPort.getByIdInMemory(transactionId))
                .thenReturn(Optional.of(transaction));

        // -- 02_Ação
        var result = transactionItauService.getById(transactionId);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(transactionId, result.getId());

        verify(transactionItauPort, times(1))
                .getByIdInMemory(transactionId);
    }

    @Test
    @DisplayName("Deve lançar exception não encontrar transaction por ID")
    void throwExceptionWhenTransactionIdNotFound() {
        // -- 01_Cenário
        var transactionId = UUID.randomUUID().toString();

        when(transactionItauPort.getByIdInMemory(transactionId))
                .thenReturn(Optional.empty());

        // -- 02_Ação
        var exception = assertThrows(IllegalStateException.class,
                () -> transactionItauService.getById(transactionId)
        );

        // -- 03_Verificação_Validação
        assertEquals(String.format("Nenhuma transação encontrada por meio do id da transação %s.", transactionId), exception.getMessage());

        verify(transactionItauPort, times(1))
                .getByIdInMemory(transactionId);
    }

    @Test
    @DisplayName("Deve excluir transaction por ID")
    void deleteTransactionById() {
        // -- 01_Cenário
        var transactionId = UUID.randomUUID().toString();

        doNothing().when(transactionItauPort)
                .deleteByIdInMemory(transactionId);

        // -- 02_Ação
        assertDoesNotThrow(
                () -> transactionItauService.deleteById(transactionId)
        );

        // -- 03_Verificação_Validação
        verify(transactionItauPort, times(1))
                .deleteByIdInMemory(transactionId);
    }
}