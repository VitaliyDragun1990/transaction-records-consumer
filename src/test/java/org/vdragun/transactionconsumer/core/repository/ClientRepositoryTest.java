package org.vdragun.transactionconsumer.core.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.vdragun.transactionconsumer.core.domain.Client;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("ClientRepository")
class ClientRepositoryTest {

    private static final int JACK_INN = 1234567890;

    private static final String JACK = "Jack";

    private static final String SMITH = "Smith";

    private static final String WILLIAM = "William";

    private static final int OTHER_INN = 1234567892;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void shouldReturnEmptyResultIfNoClientWithGivenInnFound() {
        Client jack = new Client(JACK_INN, JACK, SMITH, WILLIAM);
        em.persistAndFlush(jack);

        Optional<Client> result = clientRepository.findByInn(OTHER_INN);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldFindExistingClientByInn() {
        Client jack = new Client(JACK_INN, JACK, SMITH, WILLIAM);
        em.persistAndFlush(jack);

        Optional<Client> result = clientRepository.findByInn(JACK_INN);

        assertThat(result).hasValue(jack);
    }
}