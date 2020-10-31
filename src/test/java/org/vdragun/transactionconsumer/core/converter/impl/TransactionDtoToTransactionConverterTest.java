package org.vdragun.transactionconsumer.core.converter.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.vdragun.transactionconsumer.core.domain.Client;
import org.vdragun.transactionconsumer.core.domain.Currency;
import org.vdragun.transactionconsumer.core.domain.Transaction;
import org.vdragun.transactionconsumer.dto.ClientDto;
import org.vdragun.transactionconsumer.dto.TransactionDto;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TransactionDtoToTransactionConverter")
class TransactionDtoToTransactionConverterTest {

    private static final String JACK = "Jack";

    private static final String SMITH = "Smith";

    private static final String WILLIAM = "William";

    private static final int INN = 1234567890;

    private static final BigDecimal AMOUNT = BigDecimal.valueOf(1500L);

    private static final String PLACE_A = "Place A";

    private static final String CARD = "123456****1234";

    private TransactionDtoToTransactionConverter converter;

    @BeforeEach
    void setUp() {
        converter = new TransactionDtoToTransactionConverter();
    }

    @Test
    void shouldConvertProvidedTransactionDtoToTransaction() {
        ClientDto clientDto = new ClientDto(JACK, SMITH, WILLIAM, INN);
        TransactionDto transactionDto = new TransactionDto(AMOUNT, PLACE_A, Currency.USD, CARD, clientDto);
        Client expectedClient = new Client(INN, JACK, SMITH, WILLIAM);
        Transaction expectedTransaction = new Transaction(AMOUNT, PLACE_A, Currency.USD, CARD, expectedClient);

        Transaction result = converter.convert(transactionDto);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedTransaction);
    }

}