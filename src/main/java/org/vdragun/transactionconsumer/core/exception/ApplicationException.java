package org.vdragun.transactionconsumer.core.exception;

/**
 * Abstract parent class for any application-specific exception
 */
public abstract class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
