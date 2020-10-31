package org.vdragun.transactionconsumer.core.service;

import org.vdragun.transactionconsumer.core.domain.Transaction;

public interface TransactionService {

    void saveClientTransaction(Transaction transaction);

}
