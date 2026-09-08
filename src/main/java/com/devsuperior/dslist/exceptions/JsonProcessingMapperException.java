package com.devsuperior.dslist.exceptions;

import java.io.Serial;

public class JsonProcessingMapperException extends RuntimeException {

    @Serial private static final long serialVersionUID = 4158896831193686945L;

    public JsonProcessingMapperException(String message) {
        super(message);
    }

    public JsonProcessingMapperException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
