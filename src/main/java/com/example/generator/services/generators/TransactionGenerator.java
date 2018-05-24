package com.example.generator.services.generators;

import com.example.generator.model.*;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class TransactionGenerator {
    private final IntegerGenerator integerGenerator;
    private final TimestampGenerator timestampGenerator;

    public TransactionGenerator(IntegerGenerator integerGenerator, TimestampGenerator timestampGenerator) {
        this.integerGenerator = integerGenerator;
        this.timestampGenerator = timestampGenerator;
    }

    public List<Transaction> generate(Range<Integer> customerIds, Range<ZonedDateTime> dateRange, Range<Integer> itemsCount, Range<Integer> itemsQuantity, List<Product> products, int eventsCount) {
        return IntStream.range(1, eventsCount + 1).mapToObj(i -> generateSingleTransaction(i, customerIds, dateRange, itemsCount, itemsQuantity, products)).collect(Collectors.toList());
    }

    private Transaction generateSingleTransaction(int id, Range<Integer> customerIds, Range<ZonedDateTime> dateRange, Range<Integer> itemsCount, Range<Integer> itemQuantity, List<Product> products) {
        return new Transaction(
                id,
                integerGenerator.generate(customerIds),
                timestampGenerator.generate(dateRange),
                generateItems(itemsCount, itemQuantity, products)
        );
    }

    private List<Item> generateItems(Range<Integer> itemsCount, Range<Integer> itemQuantity, List<Product> products) {
        return IntStream.range(0, integerGenerator.generate(itemsCount))
                .mapToObj(i -> new Item(
                        products.get(i % products.size()).getName(),
                        integerGenerator.generate(itemQuantity),
                        products.get(i % products.size()).getPrice()
                )).collect(Collectors.toCollection(ArrayList::new));
    }
}
