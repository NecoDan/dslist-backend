package com.devsuperior.dslist.itau_v1_challenge.service;

import com.devsuperior.dslist.exceptions.EntityCreateFailedException;
import com.devsuperior.dslist.itau_v1_challenge.core.domain.TransactionItau;
import com.devsuperior.dslist.itau_v1_challenge.core.ports.TransactionItauMemoryPort;
import com.devsuperior.dslist.itau_v1_challenge.core.usecase.TransactionItauCreateUseCaseImpl;
import com.devsuperior.dslist.itau_v1_challenge.core.usecase.input.TransactionItauInput;
import com.devsuperior.dslist.util.factory.itau_challenge.ItauTransactionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionItauCreateUseCaseImplTest {

    @Mock private TransactionItauMemoryPort transactionItauMemoryPort;

    @InjectMocks private TransactionItauCreateUseCaseImpl transactionItauCreateUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar transaction retorna DTO")
    void createTransactionAndReturnAsDTO() {
        // -- 01_Cenário
        var transactionDTO = ItauTransactionFactory.buildMockTransactionItauRequestDTO();
        var createdTransaction = ItauTransactionFactory.buildMockTransactionItau();
        createdTransaction.setId(UUID.randomUUID().toString());

        when(transactionItauMemoryPort.createTransactionInMemory(any(TransactionItau.class)))
                .thenReturn(createdTransaction);

        // -- 02_Ação
        var result = transactionItauCreateUseCase.createTransaction(transactionDTO);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(createdTransaction.getId(), result.getId());

        verify(transactionItauMemoryPort, times(1))
                .createTransactionInMemory(any(TransactionItau.class));
    }

    @Test
    @DisplayName("Deve lançar exception quando o valor da transação for menor ou igual a zero")
    void throwExceptionWhenTransactionAmountIsZeroOrNegative() {
        // -- 01_Cenário
        var transactionItauInputVar1 = ItauTransactionFactory.buildMockTransactionItauRequestDTO();
        var transactionItauInputVar2 = new TransactionItauInput(BigDecimal.ZERO, transactionItauInputVar1.createdAt());

        // -- 02_Ação
        var exception = assertThrows(EntityCreateFailedException.class,
                () -> transactionItauCreateUseCase.createTransaction(transactionItauInputVar2)
        );

        // -- 03_Verificação_Validação
        assertEquals("O valor da transação deve ser maior que zero.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exception quando a data de criação for nula")
    void throwExceptionWhenTransactionCreatedAtIsNull() {
        // -- 01_Cenário
        var transactionItauInputVar1 = ItauTransactionFactory.buildMockTransactionItauRequestDTO();
        var transactionItauInputVar2 = new TransactionItauInput(transactionItauInputVar1.amount(), null);

        // -- 02_Ação
        var exception = assertThrows(EntityCreateFailedException.class,
                () -> transactionItauCreateUseCase.createTransaction(transactionItauInputVar2)
        );

        // -- 03_Verificação_Validação
        assertEquals("A data/hora de criação da transação é obrigatória. Data e hora maiores que data " +
                "atual não são permitidos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exception quando a data de criação for maior que a data atual")
    void throwExceptionWhenTransactionCreatedAtIsInFuture() {
        // -- 01_Cenário
        var transactionItauInputVar1 = ItauTransactionFactory.buildMockTransactionItauRequestDTO();

        var transactionItauInputVar2 = new TransactionItauInput(
                transactionItauInputVar1.amount(),
                OffsetDateTime.now().plusDays(1)
        );

        // -- 02_Ação
        var exception = assertThrows(EntityCreateFailedException.class,
                () -> transactionItauCreateUseCase.createTransaction(transactionItauInputVar2)
        );

        // -- 03_Verificação_Validação
        assertEquals("A data/hora de criação da transação é obrigatória. Data e hora maiores que data " +
                "atual não são permitidos.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve criar transação com valor válido e data válida")
    void createTransactionWithValidAmountAndCreatedAt() {
        // -- 01_Cenário
        var transactionItauInputVar1 = ItauTransactionFactory.buildMockTransactionItauRequestDTO();

        var transactionItauInputVar2 = new TransactionItauInput(
                transactionItauInputVar1.amount(),
                OffsetDateTime.now()
        );

        var createdTransaction = ItauTransactionFactory.buildMockTransactionItau();
        createdTransaction.setId(UUID.randomUUID().toString());

        when(transactionItauMemoryPort.createTransactionInMemory(any(TransactionItau.class)))
                .thenReturn(createdTransaction);

        // -- 02_Ação
        var result = transactionItauCreateUseCase.createTransaction(transactionItauInputVar2);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertEquals(createdTransaction.getId(), result.getId());
        verify(transactionItauMemoryPort, times(1))
                .createTransactionInMemory(any(TransactionItau.class));
    }
}