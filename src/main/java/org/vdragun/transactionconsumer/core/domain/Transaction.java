package org.vdragun.transactionconsumer.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.StringJoiner;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal amount;

    @Column
    private String place;

    @Enumerated(EnumType.STRING)
    @Column
    private Currency currency;

    @Column(name = "card")
    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    protected Transaction() {
    }

    public Transaction(BigDecimal amount, String place, Currency currency, String cardNumber, Client client) {
        this.amount = amount;
        this.place = place;
        this.currency = currency;
        this.cardNumber = cardNumber;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Transaction.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("amount=" + amount)
                .add("place='" + place + "'")
                .add("currency=" + currency)
                .add("cardNumber=" + cardNumber)
                .add("client=" + client)
                .toString();
    }
}
