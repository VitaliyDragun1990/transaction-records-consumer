package org.vdragun.transactionconsumer.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.vdragun.transactionconsumer.core.converter.TypeConverter;
import org.vdragun.transactionconsumer.core.domain.Transaction;
import org.vdragun.transactionconsumer.core.service.TransactionRecordsResourceReader;
import org.vdragun.transactionconsumer.core.service.exception.ResourceReadingException;
import org.vdragun.transactionconsumer.dto.TransactionDto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

@Service
public class TransactionRecordsXmlResourceReader implements TransactionRecordsResourceReader {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionRecordsXmlResourceReader.class);

    private static final String TRANSACTION = "transaction";

    private final TypeConverter<TransactionDto, Transaction> transactionConverter;

    public TransactionRecordsXmlResourceReader(TypeConverter<TransactionDto, Transaction> transactionConverter) {
        this.transactionConverter = transactionConverter;
    }

    @Override
    public int readTransactionRecords(Resource resource, Consumer<Transaction> handler) {
        try (InputStream resourceInputStream = resource.getInputStream()) {

            int transactionRecordsCount = readTransactionRecords(resourceInputStream, handler);

            LOG.info("In readTransactionRecords - Successfully read [{}] transaction records from given XML resource",
                    transactionRecordsCount);

            return transactionRecordsCount;

        } catch (JAXBException | IOException | XMLStreamException e) {
            throw new ResourceReadingException("Fail to read transaction records from given resource:" + e.getMessage(), e);
        }
    }

    private int readTransactionRecords(InputStream inputStream, Consumer<Transaction> handler) throws JAXBException, XMLStreamException {
        Unmarshaller unmarshaller = getUnmarshaller();
        XMLStreamReader reader = getReader(inputStream);

        skipUntilTransactionRecord(reader);

        int transactionRecordsCount = 0;
        while (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
            JAXBElement<TransactionDto> transactionElement = unmarshaller.unmarshal(reader, TransactionDto.class);
            TransactionDto transactionDto = transactionElement.getValue();
            handler.accept(transactionConverter.convert(transactionDto));

            transactionRecordsCount++;

            skipToNextTransactionRecordIfAny(reader);
        }
        reader.close();

        return transactionRecordsCount;
    }

    private void skipToNextTransactionRecordIfAny(XMLStreamReader reader) throws XMLStreamException {
        while (reader.getEventType() == XMLStreamConstants.CHARACTERS) {
            reader.next();
        }
    }

    private void skipUntilTransactionRecord(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext() && (!reader.isStartElement() || ! reader.getLocalName().equals(TRANSACTION))) {
            reader.next();
        }
    }

    private XMLStreamReader getReader(InputStream inputStream) throws XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        return xmlInputFactory.createXMLStreamReader(inputStream);
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TransactionDto.class);
        return jaxbContext.createUnmarshaller();
    }
}
