package com.example.apptive19thhjfundbackend.user.config;

import com.example.apptive19thhjfundbackend.user.data.dto.StockDto;
import com.example.apptive19thhjfundbackend.user.data.entity.Stock;
import com.example.apptive19thhjfundbackend.user.data.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CsvWriter implements ItemWriter<StockDto> {
    private final StockRepository stockRepository;

    @Override
    public void write(List<? extends StockDto> items) throws Exception {
        List<Stock> stockList = new ArrayList<>();

        items.forEach(getStockDto -> {
//            System.out.println(getStockDto.getName());

            Stock stock = getStockDto.toEntity();
            stockList.add(stock);

        });

        stockRepository.saveAll(stockList);
    }
}

