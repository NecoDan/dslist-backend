package com.devsuperior.dslist.util.factory.itau_challenge;

import com.devsuperior.dslist.itau_v1_challenge.core.domain.TransactionItau;
import com.devsuperior.dslist.itau_v1_challenge.core.usecase.input.TransactionItauInput;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public final class ItauTransactionFactory {

    private ItauTransactionFactory() {
        throw new IllegalStateException("Utility class ItauTransactionFactory");
    }

    public static TransactionItau buildMockTransactionItau() {
        return TransactionItau.builder()
                .id(UUID.randomUUID().toString())
                .amount(BigDecimal.valueOf(100.00))
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static TransactionItauInput buildMockTransactionItauRequestDTO() {
        return new TransactionItauInput(
                BigDecimal.valueOf(100.00),
                LocalDateTime.now().atOffset(java.time.ZoneOffset.UTC)
        );
    }
}
