package com.example.generator.services.parsers;

import com.example.generator.model.Range;
import org.springframework.stereotype.Component;

@Component
public class IntegerRangeParser {
    public Range<Integer> parse(String valueToParse) {
        try {
            String[] parts = valueToParse.split(":");
            return new Range<>(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
        } catch (Exception e) {
            throw new ParseException(e.getMessage());
        }
    }
}