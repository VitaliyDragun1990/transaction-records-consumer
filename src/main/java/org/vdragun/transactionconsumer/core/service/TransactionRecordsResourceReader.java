package org.vdragun.transactionconsumer.core.service;

import org.springframework.core.io.Resource;
import org.vdragun.transactionconsumer.core.domain.Transaction;
import org.vdragun.transactionconsumer.core.service.exception.ResourceReadingException;

import java.util.function.Consumer;

/**
 * Component responsible for reading {@link Transaction} records from
 * provided {@link Resource}
 */
public interface TransactionRecordsResourceReader {

    /**
     * Reads {@link Transaction} records from specified {@link Resource} and passes
     * each such record to provided {@link Consumer} handler for further processing
     *
     * @param resource - resource from which {@link Transaction} records should be read
     * @param handler - {@link Consumer} implementation which {@code accept} method will be
     *                called for each successfully read transaction instance.
     * @return number of transaction records read
     */
    int readTransactionRecords(Resource resource, Consumer<Transaction> handler) throws ResourceReadingException;
}
