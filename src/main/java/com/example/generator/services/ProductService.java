package com.example.generator.services;

import com.example.generator.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class ProductService {

    private static final String URL = "https://csv-items-generator.herokuapp.com/items";

    public List<Product> getProducts() {
        log.info("getProducts start...");
        String response = doGet();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Product[] products = objectMapper.readValue(response, Product[].class);
            return Arrays.asList(products);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String doGet() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);
        return responseEntity.getBody();
    }
}
