package com.example.apptive19thhjfundbackend.stock.service;

import com.example.apptive19thhjfundbackend.stock.data.dto.StockDto;
import com.example.apptive19thhjfundbackend.stock.data.entity.Stock;

import java.util.List;

public interface StockService {
    List<StockDto> findStocks(String key);
    List<StockDto> stockLists();

    void save(Stock stock);
}
