package com.devsuperior.dslist.picpay_challenge.domain;

import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction implements Serializable {

    private Long id;
    private BigDecimal amount;
    private User sender;
    private User receiver;
    private LocalDateTime createdAt;
}
