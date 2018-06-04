package com.example.generator.model;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Ranges {
    private final Range<Integer> customerIds;
    private final Range<ZonedDateTime> dateRange;
    private final Range<Integer> itemsCount;
    private final Range<Integer> itemsQuantity;
}
