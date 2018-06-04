package com.example.generator.services;

import com.example.generator.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(ProductService.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void shouldGetProducts() throws JsonProcessingException {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Product> products = new ArrayList<>(
                Collections.singletonList(
                        new Product("name", BigDecimal.ONE)
                ));
        String productsJson = objectMapper.writeValueAsString(products);
        this.server
                .expect(anything())
                .andRespond(withSuccess(productsJson, MediaType.APPLICATION_JSON));

        // when
        List<Product> result = productService.getProducts();

        // then
        assertThat(result).isEqualTo(products);
    }
}
