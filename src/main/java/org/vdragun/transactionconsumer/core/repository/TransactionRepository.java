package org.vdragun.transactionconsumer.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vdragun.transactionconsumer.core.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
