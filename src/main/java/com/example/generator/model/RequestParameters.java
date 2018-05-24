package com.example.generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class RequestParameters {
    private final Optional<String> customerIds;
    private final Optional<String> dateRange;
    private final Optional<String> itemsCount;
    private final Optional<String> itemsQuantity;
    private final Optional<String> eventsCount;
}