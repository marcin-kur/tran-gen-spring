package com.example.generator.services.parsers;

import org.springframework.stereotype.Component;

@Component
public class IntegerParser {
    public int parse(String valueToParse) {
        try {
            return Integer.valueOf(valueToParse);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage());
        }
    }
}