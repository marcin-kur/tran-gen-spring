package com.example.generator.services;

import com.example.generator.model.InputParameters;
import com.example.generator.model.Product;
import com.example.generator.model.RequestParameters;
import com.example.generator.model.Transaction;
import com.example.generator.services.generators.TransactionGenerator;
import com.example.generator.services.parsers.InputParametersCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionService {

    private final InputParametersCreator inputParametersCreator;
    private final ProductService productService;
    private final TransactionGenerator transactionGenerator;

    @Autowired
    public TransactionService(InputParametersCreator inputParametersCreator,
                              ProductService productService,
                              TransactionGenerator transactionGenerator) {
        this.inputParametersCreator = inputParametersCreator;
        this.productService = productService;
        this.transactionGenerator = transactionGenerator;
    }

    public List<Transaction> generateTransactions(RequestParameters requestParameters) {
        InputParameters inputParameters = inputParametersCreator.create(requestParameters);
        List<Product> products = productService.getProducts();
        return transactionGenerator.generate(
                inputParameters.getCustomerIds(),
                inputParameters.getDateRange(),
                inputParameters.getItemsCount(),
                inputParameters.getItemsQuantity(),
                products,
                inputParameters.getEventsCount()
        );
    }
}
