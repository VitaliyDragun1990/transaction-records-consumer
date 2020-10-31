package org.vdragun.transactionconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vdragun.transactionconsumer.core.repository.ClientRepository;
import org.vdragun.transactionconsumer.core.repository.TransactionRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TransactionRecordsConsumerApplicationTests {

    private static final int EXPECTED_NUMBER_OF_TRANSACTIONS = 12;

    private static final int EXPECTED_NUMBER_OF_CLIENTS = 3;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void shouldPopulateDatabaseWithTransactionRecordsOnStartup() {
        assertThat(transactionRepository.count()).isEqualTo(EXPECTED_NUMBER_OF_TRANSACTIONS);
        assertThat(clientRepository.count()).isEqualTo(EXPECTED_NUMBER_OF_CLIENTS);
    }

}
