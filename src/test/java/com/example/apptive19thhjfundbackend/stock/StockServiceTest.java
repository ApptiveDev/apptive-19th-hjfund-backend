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

    @Test
    void 데이터탐색시간() {
        for(int i=0; i<20; i++) {
        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        List<StockDto> stocks1 = stockService.findStocks("K");
        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        double secDiffTime = (afterTime - beforeTime)/1000.; //두 시간에 차 계산
        System.out.println( "개수는 " + stocks1.size() + "개 /"+(i+1) +"번째 시간차이(m) : "+ secDiffTime);
        }
    }

}
