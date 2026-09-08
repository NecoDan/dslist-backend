package com.devsuperior.dslist.exceptions;

import java.io.Serial;

public class TransactionPicPayNotAuthorizedExcpetion extends RuntimeException {

    @Serial private static final long serialVersionUID = 3621731147534707019L;

    public TransactionPicPayNotAuthorizedExcpetion(String message) {
        super(message);
    }

    public TransactionPicPayNotAuthorizedExcpetion(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
