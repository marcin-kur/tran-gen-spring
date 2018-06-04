package com.example.generator.services;

import com.example.generator.model.*;
import com.example.generator.services.generators.TransactionGenerator;
import com.example.generator.services.parsers.InputParametersCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class TransactionServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private RequestParameters requestParameters;

    @Mock
    private InputParametersCreator inputParametersCreator;

    @Mock
    private ProductService productService;

    @Mock
    private TransactionGenerator transactionGenerator;

    @Test
    public void shouldGenerateTransactions() throws JsonProcessingException {
        // given
        ArrayList<Product> products = new ArrayList<>(
                Collections.singletonList(
                        new Product("name", BigDecimal.ONE)
                )
        );
        when(productService.getProducts()).thenReturn(products);
        when(inputParametersCreator.create(requestParameters)).thenReturn(mock(InputParameters.class));
        TransactionService transactionService = new TransactionService(inputParametersCreator, productService, transactionGenerator);

        // when
        transactionService.generateTransactions(requestParameters);

        // then
        verify(inputParametersCreator, times(1)).create(requestParameters);
        verify(productService, times(1)).getProducts();
    }
}
