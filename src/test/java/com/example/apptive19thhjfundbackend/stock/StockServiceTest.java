package com.example.apptive19thhjfundbackend.stock;

import com.example.apptive19thhjfundbackend.stock.data.dto.StockDto;
import com.example.apptive19thhjfundbackend.stock.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class StockServiceTest {
    @Autowired
    StockService stockService;

}
