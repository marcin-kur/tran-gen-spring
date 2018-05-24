package com.example.generator.controllers;

import com.example.generator.model.RequestParameters;
import com.example.generator.model.Transaction;

import com.example.generator.services.TransactionService;
import com.example.generator.services.YamlWriter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class TransactionController {

    private final TransactionService transactionService;
    private final YamlWriter yamlWriter;

    @Autowired
    TransactionController(TransactionService transactionService, YamlWriter yamlWriter) {
        this.transactionService = transactionService;
        this.yamlWriter = yamlWriter;
    }

    @RequestMapping(value = "transactions", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @ResponseBody
    public List<Transaction> getAllInJsonOrXml(
            @RequestParam("customerIds") Optional<String> customerIds,
            @RequestParam("dateRange") Optional<String> dateRange,
            @RequestParam("itemsCount") Optional<String> itemsCount,
            @RequestParam("itemsQuantity") Optional<String> itemsQuantity,
            @RequestParam("eventsCount") Optional<String> eventsCount
    ) {
        RequestParameters requestParameters = new RequestParameters(customerIds, dateRange, itemsCount, itemsQuantity, eventsCount);
        return transactionService.generateTransactions(requestParameters);
    }

    @RequestMapping(value = "transactions", method = RequestMethod.GET, produces = {"application/yaml"})
    @ResponseBody
    public String getAllInYaml(
            @RequestParam("customerIds") Optional<String> customerIds,
            @RequestParam("dateRange") Optional<String> dateRange,
            @RequestParam("itemsCount") Optional<String> itemsCount,
            @RequestParam("itemsQuantity") Optional<String> itemsQuantity,
            @RequestParam("eventsCount") Optional<String> eventsCount
    ) throws JsonProcessingException {
        RequestParameters requestParameters = new RequestParameters(customerIds, dateRange, itemsCount, itemsQuantity, eventsCount);
        List<Transaction> transactions = transactionService.generateTransactions(requestParameters);
        return yamlWriter.writeValueAsString(transactions);
    }
}
