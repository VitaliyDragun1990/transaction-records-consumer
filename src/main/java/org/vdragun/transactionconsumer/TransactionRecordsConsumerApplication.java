package org.vdragun.transactionconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TransactionRecordsConsumerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder()
                .sources(TransactionRecordsConsumerApplication.class)
                .web(WebApplicationType.NONE)
                .build();

        app.run(args).close();
    }
}
