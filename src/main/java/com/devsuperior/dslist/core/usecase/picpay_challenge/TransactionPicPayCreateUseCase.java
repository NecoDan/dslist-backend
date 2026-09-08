package com.devsuperior.dslist.core.usecase.picpay_challenge;

import com.devsuperior.dslist.core.usecase.picpay_challenge.input.TransactionPicPayInput;
import com.devsuperior.dslist.core.usecase.picpay_challenge.output.TransactionPicPayOutput;

public interface TransactionPicPayCreateUseCase {
    TransactionPicPayOutput createTransaction(TransactionPicPayInput input);
}
