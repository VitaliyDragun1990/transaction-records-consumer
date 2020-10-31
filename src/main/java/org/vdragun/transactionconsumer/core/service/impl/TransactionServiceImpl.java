package org.vdragun.transactionconsumer.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vdragun.transactionconsumer.core.domain.Client;
import org.vdragun.transactionconsumer.core.domain.Transaction;
import org.vdragun.transactionconsumer.core.repository.ClientRepository;
import org.vdragun.transactionconsumer.core.repository.TransactionRepository;
import org.vdragun.transactionconsumer.core.service.TransactionService;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final ClientRepository clientRepository;

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(ClientRepository clientRepository, TransactionRepository transactionRepository) {
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public void saveClientTransaction(Transaction transaction) {
        transaction.setClient(saveClientIfNotExist(transaction.getClient()));

        Transaction savedTransaction = transactionRepository.save(transaction);
        LOG.debug("Saved new transaction:{}", savedTransaction);
    }

    private Client saveClientIfNotExist(Client client) {
        Optional<Client> clientOptional = clientRepository.findByInn(client.getInn());

        return clientOptional.orElseGet(() -> clientRepository.save(client));
    }
}
