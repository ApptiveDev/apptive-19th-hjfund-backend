package com.example.apptive19thhjfundbackend.stock.controller;

import com.example.apptive19thhjfundbackend.stock.data.dto.StockDto;
import com.example.apptive19thhjfundbackend.stock.data.entity.Stock;
import com.example.apptive19thhjfundbackend.stock.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping(value = "/stock")
    public List<StockDto> search(@RequestParam String key) {

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        System.out.println("이전시간" + beforeTime);
        List<StockDto> stocks =  stockService.findStocks(key);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        System.out.println("이후시간" + afterTime);
        double secDiffTime = (afterTime - beforeTime)/1000.; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
        return stocks;
    }
    @GetMapping(value = "/stocks")
    public List<StockDto> findAll() {
        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        System.out.println("이전시간" + beforeTime);
        List<StockDto> stocks =  stockService.stockLists();

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        System.out.println("이후시간" + afterTime);
        double secDiffTime = (afterTime - beforeTime)/1000.; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
        return stocks;
    }

    @GetMapping(value = "/dummy")
    public void dummy() {
//        List<StockDto> stocks = stockService.stockLists();
//        for(int i=99; i<=99; i++) {
//            for (StockDto stockDto:stocks) {
//                String s = stockDto.getName() + i;
//                Stock stock = Stock.builder()
//                        .code(stockDto.getCode())
//                        .name(s)
//                        .build();
//                stockService.save(stock);
//            }
//        }
    }

}
