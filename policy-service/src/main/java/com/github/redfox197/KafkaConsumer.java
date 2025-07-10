package com.github.redfox197;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.time.LocalDate;

@ApplicationScoped
public class KafkaConsumer {

    @Incoming("policy-create")
    @Transactional
    public void test(Product product) {
        LocalDate date = LocalDate.now();

        Policy policy = new Policy();
        policy.code = "PO-" + product.id();
        policy.product_id = product.id();
        policy.creationDate = date;
        policy.expirationDate = date.plusMonths(1);

        policy.persist();
    }
}
