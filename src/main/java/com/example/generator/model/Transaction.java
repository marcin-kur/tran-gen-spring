package com.example.generator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Transaction {
    public int id;
    @JsonProperty("customer_id")
    private final int customerId;
    private final ZonedDateTime timestamp;
    private final List<Item> items;
    private final BigDecimal sum;

    public Transaction(int id, int customerId, ZonedDateTime timestamp, List<Item> items) {
        this.id = id;
        this.customerId = customerId;
        this.timestamp = timestamp;
        this.items = items;
        this.sum = calculateSum();
    }

    private BigDecimal calculateSum() {
        return items
                .stream()
                .map(Item::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
