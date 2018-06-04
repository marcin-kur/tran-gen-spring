package com.example.generator.services.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(getSerializedValue(value));
    }

    private String getSerializedValue(ZonedDateTime value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return formatter.format(value);
    }
}