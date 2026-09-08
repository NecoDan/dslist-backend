package com.devsuperior.dslist.core.usecase.picpay_challenge;

import com.devsuperior.dslist.core.usecase.picpay_challenge.output.TransactionPicPayOutput;

import java.util.List;

public interface TransactionPicPayGetsUseCase {
    List<TransactionPicPayOutput> getAll();

    List<TransactionPicPayOutput> getAllWithForEach();

    TransactionPicPayOutput getById(Long id);
}
