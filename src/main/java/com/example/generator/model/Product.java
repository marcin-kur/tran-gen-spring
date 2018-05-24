package com.example.generator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private final String name;
    private final BigDecimal price;

    @JsonCreator
    public Product(@JsonProperty("name") String name, @JsonProperty("price") BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
