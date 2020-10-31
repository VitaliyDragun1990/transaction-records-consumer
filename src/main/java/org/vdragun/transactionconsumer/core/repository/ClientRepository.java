package org.vdragun.transactionconsumer.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vdragun.transactionconsumer.core.domain.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByInn(Integer inn);
}
