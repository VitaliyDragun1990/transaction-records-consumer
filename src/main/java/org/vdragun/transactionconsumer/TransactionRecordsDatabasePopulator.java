package org.vdragun.transactionconsumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.vdragun.transactionconsumer.core.service.TransactionRecordsResourceReader;
import org.vdragun.transactionconsumer.core.service.TransactionService;

/**
 * Populates application database with transaction records read from XML file
 */
@Component
public class TransactionRecordsDatabasePopulator implements CommandLineRunner {

    private final TransactionRecordsResourceReader transactionRecordsReader;

    private final TransactionService transactionService;

    private final Resource transactionRecordsResource;

    public TransactionRecordsDatabasePopulator(
            TransactionRecordsResourceReader transactionRecordsReader,
            TransactionService transactionService,
            @Value("${transactions.resource.xml.path}") String resourceFilePath) {
        this.transactionRecordsReader = transactionRecordsReader;
        this.transactionService = transactionService;
        this.transactionRecordsResource = new ClassPathResource(resourceFilePath);
    }

    @Override
    public void run(String... args) {
        transactionRecordsReader.readTransactionRecords(transactionRecordsResource, transactionService::saveClientTransaction);
    }
}
