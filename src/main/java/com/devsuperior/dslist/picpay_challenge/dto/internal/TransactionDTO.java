package com.devsuperior.dslist.picpay_challenge.dto.internal;


import java.math.BigDecimal;

public record TransactionDTO (BigDecimal value, Long senderId, Long receiverId) {
}
