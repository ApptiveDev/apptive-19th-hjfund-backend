package com.example.apptive19thhjfundbackend.user.service.Impl;

import com.example.apptive19thhjfundbackend.user.data.dto.StockDto;
import com.example.apptive19thhjfundbackend.user.data.entity.Stock;
import com.example.apptive19thhjfundbackend.user.data.repository.StockRepository;
import com.example.apptive19thhjfundbackend.user.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {
    public StockRepository stockRepository;


    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<StockDto> findStocks(String key) {
        List<Stock> stocks = stockRepository.findByNameStartingWith(key);
        return stocks.stream().map(stock -> stock.toDto()).collect(Collectors.toList());
    }

    @Override
    public List<StockDto> findStocks(char key) {
        List<Stock> stocks = stockRepository.findByNameStartingWith(key);
        return stocks.stream().map(stock -> stock.toDto()).collect(Collectors.toList());
    }

    @Override
    public List<StockDto> stockLists() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream().map(stock -> stock.toDto()).collect(Collectors.toList());
    }

}
