package org.vdragun.transactionconsumer.core.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.vdragun.transactionconsumer.core.converter.TypeConverter;
import org.vdragun.transactionconsumer.core.domain.Client;
import org.vdragun.transactionconsumer.core.domain.Transaction;
import org.vdragun.transactionconsumer.core.service.exception.ResourceReadingException;
import org.vdragun.transactionconsumer.dto.ClientDto;
import org.vdragun.transactionconsumer.dto.TransactionDto;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.vdragun.transactionconsumer.core.domain.Currency.UAH;
import static org.vdragun.transactionconsumer.core.domain.Currency.USD;

@ExtendWith(MockitoExtension.class)
@DisplayName("TransactionRecordsXmlResourceReader")
class TransactionRecordsXmlResourceReaderTest {

    private static final String PLACE_ONE = "A PLACE 1";

    private static final String PLACE_TWO = "A PLACE 2";

    private static final BigDecimal AMOUNT_A = BigDecimal.valueOf(10.01);

    private static final BigDecimal AMOUNT_B = BigDecimal.valueOf(9876.01);

    private static final String CARD = "123456****1234";

    private static final Integer INN = 1234567890;

    private static final String FIRST_NAME = "Ivan";

    private static final String LAST_NAME = "Ivanoff";

    private static final String MIDDLE_NAME = "Ivanoff";

    private static final String VALID_FILE = "test_data.xml";

    private static final String INVALID_FILE = "invalid_test_data.xml";

    private static final int TOTAL_TRANSACTION_RECORDS = 2;

    @Mock
    private TypeConverter<TransactionDto, Transaction> converterMock;

    @Mock
    private Consumer<Transaction> handlerMock;

    @Captor
    private ArgumentCaptor<TransactionDto> transactionDtoCaptor;

    @InjectMocks
    private TransactionRecordsXmlResourceReader transactionReader;

    @Test
    void shouldReadTransactionRecordsFromProvidedXmlResource() {
        Transaction dummyTransaction = dummyTransaction();
        ClientDto expectedClientDto = new ClientDto(FIRST_NAME, LAST_NAME, MIDDLE_NAME, INN);
        TransactionDto firstExpectedTransaction = new TransactionDto(AMOUNT_A, PLACE_ONE, UAH, CARD, expectedClientDto);
        TransactionDto secondExpectedTransaction = new TransactionDto(AMOUNT_B, PLACE_TWO, UAH, CARD, expectedClientDto);
        when(converterMock.convert(any(TransactionDto.class))).thenReturn(dummyTransaction);

        int recordsRead = transactionReader.readTransactionRecords(resourceFrom(VALID_FILE), handlerMock);

        verify(converterMock, times(2)).convert(transactionDtoCaptor.capture());
        verify(handlerMock, times(2)).accept(eq(dummyTransaction));
        assertThat(transactionDtoCaptor.getAllValues()).containsExactly(firstExpectedTransaction, secondExpectedTransaction);
        assertThat(recordsRead).isEqualTo(TOTAL_TRANSACTION_RECORDS);
    }

    @Test
    void shouldThrowExceptionIfTryToReadInvalidXmlFile() {
        Resource invalidFileResource = resourceFrom(INVALID_FILE);
        assertThrows(ResourceReadingException.class, () ->
                transactionReader.readTransactionRecords(invalidFileResource, handlerMock));

        verifyNoInteractions(converterMock);
        verifyNoInteractions(handlerMock);
    }

    private Resource resourceFrom(String filePath) {
        return new ClassPathResource(filePath);
    }

    private Transaction dummyTransaction() {
        Client dummyClient = new Client(INN, FIRST_NAME, LAST_NAME, MIDDLE_NAME);
        return new Transaction(AMOUNT_A, PLACE_ONE, USD, CARD, dummyClient);
    }
}