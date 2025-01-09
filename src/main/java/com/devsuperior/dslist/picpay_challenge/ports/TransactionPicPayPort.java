package com.devsuperior.dslist.picpay_challenge.ports;

import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionDTO;

public interface TransactionPicPayPort {
    void createTransaction(TransactionDTO transactionDTO) throws Exception;
}
