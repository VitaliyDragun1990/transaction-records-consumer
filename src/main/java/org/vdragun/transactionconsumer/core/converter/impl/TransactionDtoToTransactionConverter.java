package org.vdragun.transactionconsumer.core.converter.impl;

import org.springframework.stereotype.Component;
import org.vdragun.transactionconsumer.core.converter.TypeConverter;
import org.vdragun.transactionconsumer.core.domain.Client;
import org.vdragun.transactionconsumer.core.domain.Transaction;
import org.vdragun.transactionconsumer.dto.ClientDto;
import org.vdragun.transactionconsumer.dto.TransactionDto;

@Component
public class TransactionDtoToTransactionConverter implements TypeConverter<TransactionDto, Transaction> {

    @Override
    public Transaction convert(TransactionDto dto) {
        ClientDto clientDto = dto.getClient();
        Client client = new Client(clientDto.getInn(), clientDto.getFirstName(), clientDto.getLastName(), clientDto.getMiddleName());

        return new Transaction(
                dto.getAmount(),
                dto.getPlace(),
                dto.getCurrency(),
                dto.getCard(),
                client);
    }
}
