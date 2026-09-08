package com.devsuperior.dslist.picpay_challenge.service;

import com.devsuperior.dslist.core.domain.picpay_challenge.TransactionPicPay;
import com.devsuperior.dslist.core.domain.picpay_challenge.UserPicPay;
import com.devsuperior.dslist.core.ports.picpay_challenge.TransactionPicPayPort;
import com.devsuperior.dslist.core.usecase.picpay_challenge.TransactionPicPayGetsUseCaseImpl;
import com.devsuperior.dslist.core.usecase.picpay_challenge.output.TransactionPicPayOutput;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class TransactionPicPayGetsUseCaseImplTest {

    @Mock
    private TransactionPicPayPort transactionPicPayPort;

    @InjectMocks
    private TransactionPicPayGetsUseCaseImpl transactionPicPayGetsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private TransactionPicPay getBuildTransaction() {
        return TransactionPicPay.builder()
                .id(RandomUtils.secure().randomLong())
                .createdAt(LocalDateTime.now())
                .receiver(UserPicPay.builder()
                        .id(RandomUtils.secure().randomLong())
                        .document("12345678900")
                        .balance(BigDecimal.valueOf(RandomUtils.secure().randomDouble()))
                        .build())
                .sender(UserPicPay.builder()
                        .id(RandomUtils.secure().randomLong())
                        .document("98765432100")
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
        final var listResult = transactionPicPayGetsUseCase.getAll();

        // -- 03_Verificação_Validação
        assertNotNull(listResult);
        assertFalse(CollectionUtils.isEmpty(listResult));
        assertEquals(2, listResult.size());

        final var optionalfirst = listResult.stream().findFirst();
        assertNotNull(optionalfirst);
        assertFalse(optionalfirst.isEmpty());
        assertNotNull(optionalfirst.get());
        assertInstanceOf(TransactionPicPayOutput.class, optionalfirst.get());
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
        final var listResult = transactionPicPayGetsUseCase.getAllWithForEach();

        // -- 03_Verificação_Validação
        assertNotNull(listResult);
        assertFalse(CollectionUtils.isEmpty(listResult));
        assertEquals(3, listResult.size());

        final var optionalfirst = listResult.stream().findFirst();
        assertNotNull(optionalfirst);
        assertFalse(optionalfirst.isEmpty());
        assertNotNull(optionalfirst.get());
        assertInstanceOf(TransactionPicPayOutput.class, optionalfirst.get());
    }

    @Test
    void getById() {
        // -- 01_Cenário
        final var transactionVar14 = Optional.of(getBuildTransaction());
        final var id = transactionVar14.get().getId();

        when(transactionPicPayPort.getById(anyLong()))
                .thenReturn(transactionVar14);

        // -- 02_Ação
        final var result = transactionPicPayGetsUseCase.getById(id);

        // -- 03_Verificação_Validação
        assertNotNull(result);
        assertInstanceOf(TransactionPicPayOutput.class, result);

        assertNotNull(result.id());
        assertInstanceOf(Long.class, result.id());
        assertEquals(id, result.id());
    }
}