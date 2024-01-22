package com.example.apptive19thhjfundbackend.stock.service.Impl;

import com.example.apptive19thhjfundbackend.stock.data.dto.StockDto;
import com.example.apptive19thhjfundbackend.stock.data.dto.StockResponseDto;
import com.example.apptive19thhjfundbackend.stock.data.entity.Stock;
import com.example.apptive19thhjfundbackend.stock.data.repository.StockRepository;
import com.example.apptive19thhjfundbackend.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public StockResponseDto findStocksByName(String key, Pageable pageable) {
        Page<Stock> stocks = stockRepository.findByNameStartingWith(key.toUpperCase(), pageable);
        List<StockDto> collect = stocks.stream().map(stock -> stock.toDto()).collect(Collectors.toList());
        return new StockResponseDto(collect, stocks.getTotalPages(), stocks.getNumberOfElements(), stocks.getSize(), stocks.getNumber());
    }

    @Override
    public StockResponseDto findStocksByCode(int key, Pageable pageable) {
        Page<Stock> stocks = stockRepository.findByCodeStartingWith(key, pageable);
        List<StockDto> collect = stocks.stream().map(stock -> stock.toDto()).collect(Collectors.toList());
        return new StockResponseDto(collect, stocks.getTotalPages(), stocks.getNumberOfElements(), stocks.getSize(), stocks.getNumber());

    }

    @Override
    public StockResponseDto stockLists() {
        List<Stock> stocks = stockRepository.findAll();
        List<StockDto> collect = stocks.stream().map(stock -> stock.toDto()).collect(Collectors.toList());
        return new StockResponseDto(collect, 1, stocks.size(), stocks.size(), stocks.size());

    }

    @Override
    public void save(Stock stock) {
        stockRepository.save(stock);
    }
}
