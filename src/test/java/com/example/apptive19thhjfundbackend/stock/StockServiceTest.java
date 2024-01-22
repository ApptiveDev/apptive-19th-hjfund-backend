package com.example.apptive19thhjfundbackend.stock;

import com.example.apptive19thhjfundbackend.stock.data.dto.StockDto;
import com.example.apptive19thhjfundbackend.stock.data.entity.Stock;
import com.example.apptive19thhjfundbackend.stock.data.repository.StockRepository;
import com.example.apptive19thhjfundbackend.stock.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class StockServiceTest {
    @Autowired
    StockRepository stockRepository;

    @Test
    void find() {
        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<Stock> h = stockRepository.findByNameStartingWith("CJ ", pageRequest);
        System.out.println(h);

    }

}
