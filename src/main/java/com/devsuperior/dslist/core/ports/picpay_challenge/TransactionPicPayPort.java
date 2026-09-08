package com.devsuperior.dslist.core.ports.picpay_challenge;

import com.devsuperior.dslist.core.domain.picpay_challenge.TransactionPicPay;

import java.util.List;
import java.util.Optional;

public interface TransactionPicPayPort {
    void createTransaction(TransactionPicPay transactionPicPayDTO);

    List<TransactionPicPay> getAllTransactions();

    Optional<TransactionPicPay> getById(Long id);
}
