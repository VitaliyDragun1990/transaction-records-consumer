CREATE TABLE clients
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    inn         INT          NOT NULL,
    first_name  VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    last_name   VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UK_client_inn UNIQUE (inn)
);

CREATE TABLE transactions
(
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    amount    DECIMAL      NOT NULL,
    place     VARCHAR(255) NOT NULL,
    currency  VARCHAR(3)   NOT NULL,
    card      VARCHAR(19)  NOT NULL,
    client_id BIGINT       NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_transaction_client FOREIGN KEY (client_id) REFERENCES clients (id)
);