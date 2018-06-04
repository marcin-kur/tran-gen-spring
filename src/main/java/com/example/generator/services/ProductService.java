package com.example.generator.services;

import com.example.generator.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Value("${url.products}")
    private String URL;
    private final RestTemplate restTemplate;

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    List<Product> getProducts() {
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

    private String doGet() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);
        return responseEntity.getBody();
    }
}
