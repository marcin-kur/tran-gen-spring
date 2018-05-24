package com.example.generator.services;

import com.example.generator.services.parsers.ParseException;
import com.example.generator.services.serializers.ZonedDateTimeSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Slf4j
@Component
public class YamlWriter {

    private YAMLMapper yamlMapper;

    public YamlWriter() {
        yamlMapper = new YAMLMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        yamlMapper.registerModule(javaTimeModule);
    }

    public String writeValueAsString(Object object) {
        try {
            return yamlMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Could not write object as a yaml string");
            throw new ParseException("Could not write object as a yaml string");
        }
    }
}
