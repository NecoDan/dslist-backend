package com.devsuperior.dslist.exceptions;

import jakarta.persistence.EntityNotFoundException;

import java.io.Serial;

public class TransactionPicPayNotFoundExcpetion extends EntityNotFoundException {

    @Serial private static final long serialVersionUID = 3621731147534707019L;

    public TransactionPicPayNotFoundExcpetion(String message) {
        super(message);
    }
}
