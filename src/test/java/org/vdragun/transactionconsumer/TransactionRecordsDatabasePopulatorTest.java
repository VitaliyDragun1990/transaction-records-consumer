package org.vdragun.transactionconsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.vdragun.transactionconsumer.core.service.TransactionRecordsResourceReader;
import org.vdragun.transactionconsumer.core.service.TransactionService;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("TransactionRecordsDatabasePopulator")
class TransactionRecordsDatabasePopulatorTest {

    @Mock
    private TransactionRecordsResourceReader resourceReaderMock;

    @Mock
    private TransactionService transactionServiceMock;

    private final String testFilePath = "test_data.xml";

    private TransactionRecordsDatabasePopulator populator;

    @BeforeEach
    void setUp() {
        populator = new TransactionRecordsDatabasePopulator(resourceReaderMock, transactionServiceMock, testFilePath);
    }

    @Test
    void shouldReadTransactionRecordsWhenRunIsCalled() {
        populator.run();

        verify(resourceReaderMock, times(1)).readTransactionRecords(
                argThat(resource -> resource.equals(new ClassPathResource(testFilePath))),
                argThat(Objects::nonNull)
        );
    }
}