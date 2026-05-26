package com.devsuperior.dslist.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class TransactionItauNotFoundException extends EntityNotFoundException {

    public TransactionItauNotFoundException(String message) {
        super(message);
    }
}
