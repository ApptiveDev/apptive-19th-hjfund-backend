package com.example.apptive19thhjfundbackend.user.service;

import com.example.apptive19thhjfundbackend.user.data.dto.StockDto;

import java.util.List;

public interface StockService {
    List<StockDto> findStocks(String key);
    List<StockDto> stockLists();
}
