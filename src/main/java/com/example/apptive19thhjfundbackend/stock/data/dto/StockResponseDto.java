package com.example.apptive19thhjfundbackend.stock.data.dto;

import com.example.apptive19thhjfundbackend.stock.data.entity.Stock;
import lombok.Data;
import lombok.Getter;

import java.util.List;


@Data
public class StockResponseDto {
    private List<StockDto> stocks;
    private int totalPages;
    private int numberOfElements;
    private int size;
    private int number;

    public StockResponseDto(List<StockDto> stocks, int totalPages, int totalElements, int size, int number) {
        this.stocks = stocks;
        this.totalPages = totalPages;
        this.numberOfElements = totalElements;
        this.size = size;
        this.number = number;
    }
}
