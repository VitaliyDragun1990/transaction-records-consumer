package org.vdragun.transactionconsumer.dto;

import org.vdragun.transactionconsumer.core.domain.Currency;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

@XmlRootElement(name = "transaction")
public class TransactionDto {

    private BigDecimal amount;

    private String place;

    private Currency currency;

    private String card;

    private ClientDto client;

    public TransactionDto() {
    }

    public TransactionDto(BigDecimal amount, String place, Currency currency, String card, ClientDto client) {
        this.amount = amount;
        this.place = place;
        this.currency = currency;
        this.card = card;
        this.client = client;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public ClientDto getClient() {
        return client;
    }

    @XmlElement(name = "client")
    public void setClient(ClientDto client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransactionDto that = (TransactionDto) o;

        return Objects.equals(amount, that.amount)
                && Objects.equals(place, that.place)
                && currency == that.currency
                && Objects.equals(card, that.card)
                && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, place, currency, card, client);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransactionDto.class.getSimpleName() + "[", "]")
                .add("amount=" + amount)
                .add("place='" + place + "'")
                .add("currency=" + currency)
                .add("card='" + card + "'")
                .add("client=" + client)
                .toString();
    }
}
