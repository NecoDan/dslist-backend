package com.devsuperior.dslist.exceptions;

import java.io.Serial;

public class LoanNotAvailableException extends RuntimeException {
    @Serial private static final long serialVersionUID = -7389408912521414111L;

    public LoanNotAvailableException(String s) {
    }
}
