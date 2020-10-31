package org.vdragun.transactionconsumer.core.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdragun.transactionconsumer.core.domain.Client;
import org.vdragun.transactionconsumer.core.domain.Currency;
import org.vdragun.transactionconsumer.core.domain.Transaction;
import org.vdragun.transactionconsumer.core.repository.ClientRepository;
import org.vdragun.transactionconsumer.core.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("TransactionServiceImpl")
class TransactionServiceImplTest {

    private static final String JACK = "Jack";

    private static final String SMITH = "Smith";

    private static final String WILLIAM = "William";

    private static final int INN = 1234567890;

    private static final BigDecimal AMOUNT = BigDecimal.valueOf(1500L);

    private static final String PLACE_A = "Place A";

    private static final String CARD = "123456****1234";

    private static final long CLIENT_ID = 10L;

    @Mock
    private ClientRepository clientRepoMock;

    @Mock
    private TransactionRepository transactionRepoMock;

    @Captor
    private ArgumentCaptor<Transaction> transactionCaptor;

    @InjectMocks
    private TransactionServiceImpl service;

    @Test
    void shouldSaveProvidedTransactionWithNewClientIfNoRecordOfGivenClientFound() {
        Client client = new Client(INN, JACK, SMITH, WILLIAM);
        Transaction transaction = new Transaction(AMOUNT, PLACE_A, Currency.USD, CARD, client);
        Client savedClient = new Client(INN, JACK, SMITH, WILLIAM);
        savedClient.setId(CLIENT_ID);

        when(clientRepoMock.findByInn(eq(INN))).thenReturn(Optional.empty());
        when(clientRepoMock.save(eq(client))).thenReturn(savedClient);

        service.saveClientTransaction(transaction);

        verify(transactionRepoMock, times(1)).save(transactionCaptor.capture());
        Transaction savedTransaction = transactionCaptor.getValue();
        assertThat(savedTransaction).usingRecursiveComparison().ignoringFields("client").isEqualTo(transaction);
        assertThat(savedTransaction.getClient()).isEqualTo(savedClient);
    }

    @Test
    void shouldSaveProvidedTransactionWithExistingClientIfClientWithSuchInnFound() {
        Client client = new Client(INN, JACK, SMITH, WILLIAM);
        Transaction transaction = new Transaction(AMOUNT, PLACE_A, Currency.USD, CARD, client);
        Client existingClient = new Client(INN, JACK, SMITH, WILLIAM);
        existingClient.setId(CLIENT_ID);

        when(clientRepoMock.findByInn(eq(INN))).thenReturn(Optional.of(existingClient));

        service.saveClientTransaction(transaction);

        verify(transactionRepoMock, times(1)).save(transactionCaptor.capture());
        Transaction savedTransaction = transactionCaptor.getValue();
        assertThat(savedTransaction).usingRecursiveComparison().ignoringFields("client").isEqualTo(transaction);
        assertThat(savedTransaction.getClient()).isEqualTo(existingClient);
    }
}