package com.example.generator.services.parsers;

import com.example.generator.model.InputParameters;
import com.example.generator.model.Range;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZonedDateTime;

@Setter
@Component
public class InputParametersBuilder {

    private Range<Integer> customerIds = new Range<>(1, 20);
    private Range<ZonedDateTime> dateRange = defaultDateRange();
    private Range<Integer> itemsCount = new Range<>(1, 20);
    private Range<Integer> itemsQuantity = new Range<>(1, 20);
    private int eventsCount = 10;

    public InputParameters createInputParameters() {
        return new InputParameters(
                customerIds,
                dateRange,
                itemsCount,
                itemsQuantity,
                eventsCount
        );
    }

    private Range<ZonedDateTime> defaultDateRange() {
        return new Range<>(
                ZonedDateTime.now().with(LocalTime.MIN),
                ZonedDateTime.now().with(LocalTime.MAX)
        );
    }
}