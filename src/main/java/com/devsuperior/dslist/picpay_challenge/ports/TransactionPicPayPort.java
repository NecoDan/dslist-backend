package com.devsuperior.dslist.picpay_challenge.ports;

import com.devsuperior.dslist.picpay_challenge.domain.Transaction;
import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionDTO;

import java.util.List;

public interface TransactionPicPayPort {
    Transaction createTransaction(TransactionDTO transactionDTO) throws Exception;

    List<Transaction> getAllTransactions();
}
