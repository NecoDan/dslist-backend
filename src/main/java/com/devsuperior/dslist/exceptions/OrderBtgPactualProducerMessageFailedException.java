package com.devsuperior.dslist.exceptions;

import java.io.Serial;

public class OrderBtgPactualProducerMessageFailedException extends RuntimeException {
    @Serial private static final long serialVersionUID = 8939436836554016968L;

    public OrderBtgPactualProducerMessageFailedException(String s, Exception e) {
    }
}
