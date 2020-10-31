package org.vdragun.transactionconsumer.core.service.exception;

import org.vdragun.transactionconsumer.core.exception.ApplicationException;

public class ResourceReadingException extends ApplicationException {

    public ResourceReadingException(String message) {
        super(message);
    }

    public ResourceReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
