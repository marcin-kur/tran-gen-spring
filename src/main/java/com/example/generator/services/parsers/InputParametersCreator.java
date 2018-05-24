package com.example.generator.services.parsers;

import com.example.generator.model.InputParameters;
import com.example.generator.model.Range;
import com.example.generator.model.RequestParameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Slf4j
@Component
public class InputParametersCreator {

    private final IntegerRangeParser integerRangeParser;
    private final TimestampRangeParser timestampParser;
    private final IntegerParser integerParser;

    public InputParametersCreator(IntegerRangeParser integerRangeParser,
                                  TimestampRangeParser timestampParser,
                                  IntegerParser integerParser) {
        this.integerRangeParser = integerRangeParser;
        this.timestampParser = timestampParser;
        this.integerParser = integerParser;
    }

    public InputParameters create(RequestParameters requestParameters) {
        log.info("Input Parameters creation started.", requestParameters);
        InputParametersBuilder inputParametersBuilder = new InputParametersBuilder();

        requestParameters.getCustomerIds().ifPresent(customerIds -> setCustomerIds(inputParametersBuilder, customerIds));
        requestParameters.getDateRange().ifPresent(dateRange -> setDateRange(inputParametersBuilder, dateRange));
        requestParameters.getItemsCount().ifPresent(itemsCount -> setItemsCount(inputParametersBuilder, itemsCount));
        requestParameters.getItemsQuantity().ifPresent(itemsQuantity -> setItemsQuantity(inputParametersBuilder, itemsQuantity));
        requestParameters.getEventsCount().ifPresent(eventsCount -> setEventsCount(inputParametersBuilder, eventsCount));

        return inputParametersBuilder.createInputParameters();
    }

    private void setCustomerIds(InputParametersBuilder inputParametersBuilder, String customerIds) {
        try {
            Range<Integer> integerRange = integerRangeParser.parse(customerIds);
            inputParametersBuilder.setCustomerIds(integerRange);
        } catch (ParseException e) {
            log.warn("Exception during parsing Customer Ids. ", e);
        }
    }

    private void setDateRange(InputParametersBuilder inputParametersBuilder, String dateRange) {
        try {
            Range<ZonedDateTime> timestampRange = timestampParser.parse(dateRange);
            inputParametersBuilder.setDateRange(timestampRange);
        } catch (ParseException e) {
            log.warn("Exception during parsing Date Range: ", e);
        }
    }

    private void setItemsCount(InputParametersBuilder inputParametersBuilder, String itemsCount) {
        try {
            Range<Integer> integerRange = integerRangeParser.parse(itemsCount);
            inputParametersBuilder.setItemsCount(integerRange);
        } catch (ParseException e) {
            log.warn("Exception during parsing Items Count: ", e);
        }
    }

    private void setItemsQuantity(InputParametersBuilder inputParametersBuilder, String itemsQuantity) {
        try {
            Range<Integer> integerRange = integerRangeParser.parse(itemsQuantity);
            inputParametersBuilder.setItemsQuantity(integerRange);
        } catch (ParseException e) {
            log.warn("Exception during parsing Items Quantity: ", e);
        }
    }

    private void setEventsCount(InputParametersBuilder inputParametersBuilder, String eventsCount) {
        try {
            int intValue = integerParser.parse(eventsCount);
            inputParametersBuilder.setEventsCount(intValue);
        } catch (ParseException e) {
            log.warn("Exception during parsing Event Count: ", e);
        }
    }
}