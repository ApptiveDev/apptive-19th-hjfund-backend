package com.example.apptive19thhjfundbackend.stock.service;

import com.example.apptive19thhjfundbackend.stock.data.dto.StockDto;
import com.example.apptive19thhjfundbackend.stock.data.dto.StockResponseDto;
import com.example.apptive19thhjfundbackend.stock.data.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockService {
    StockResponseDto findStocksByName(String key, Pageable pageable);
    StockResponseDto findStocksByCode(int key, Pageable pageable);

    StockResponseDto stockLists();

    void save(Stock stock);
}
