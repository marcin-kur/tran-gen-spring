package com.example.generator.services.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Slf4j
@Component
public class YamlSerializer {

    private YAMLMapper yamlMapper;

    public YamlSerializer() {
        yamlMapper = new YAMLMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        yamlMapper.registerModule(javaTimeModule);
    }

    public String serialize(Object object) {
        try {
            return yamlMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Could not serialize object as a yaml string");
            throw new SerializeException("Could not serialize as a yaml string");
        }
    }
}
