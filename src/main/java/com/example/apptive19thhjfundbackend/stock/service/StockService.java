package com.example.apptive19thhjfundbackend.stock.service;

import com.example.apptive19thhjfundbackend.stock.data.dto.StockDto;
import com.example.apptive19thhjfundbackend.stock.data.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockService {
    Page<StockDto> findStocks(String key, Pageable pageable);
    List<StockDto> stockLists();

    void save(Stock stock);
}
