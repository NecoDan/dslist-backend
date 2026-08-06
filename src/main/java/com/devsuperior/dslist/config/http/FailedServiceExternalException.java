package com.devsuperior.dslist.config.http;

import org.springframework.http.HttpMethod;

import java.io.Serial;

public class FailedServiceExternalException extends RuntimeException {

    @Serial private static final long serialVersionUID = 4280382370059728308L;

    public FailedServiceExternalException(String message) {
        super(message);
    }

    public FailedServiceExternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedServiceExternalException(Throwable cause) {
        super(cause);
    }

    public FailedServiceExternalException(
            HttpMethod httpMethod,
            String url,
            int httpStatus,
            String message
    ) {
        super("Failed to call external service: " + httpMethod + " " + url + " returned status " + httpStatus + ". Message: " + message);
    }
}
