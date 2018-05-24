package com.example.generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class InputParameters {
    private final Range<Integer> customerIds;
    private final Range<ZonedDateTime> dateRange;
    private final Range<Integer> itemsCount;
    private final Range<Integer> itemsQuantity;
    private final int eventsCount;
}
