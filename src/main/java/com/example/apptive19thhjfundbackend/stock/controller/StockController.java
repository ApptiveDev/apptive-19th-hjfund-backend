package com.example.apptive19thhjfundbackend.stock.controller;

import com.example.apptive19thhjfundbackend.stock.data.dto.StockDto;
import com.example.apptive19thhjfundbackend.stock.data.dto.StockResponseDto;
import com.example.apptive19thhjfundbackend.stock.data.entity.Stock;
import com.example.apptive19thhjfundbackend.stock.service.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping(value = "/name")
    public StockResponseDto searchByName(@RequestParam String key, @RequestParam int count, @RequestParam int index) {
        PageRequest pageRequest = PageRequest.of(index, count);

//        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
//        System.out.println("이전시간" + beforeTime);
        StockResponseDto stock = stockService.findStocksByName(key, pageRequest);

//        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
//        System.out.println("이후시간" + afterTime);
//        double secDiffTime = (afterTime - beforeTime)/1000.; //두 시간에 차 계산
//        System.out.println("시간차이(m) : "+secDiffTime);
        return stock;
    }
    @GetMapping(value = "/code")
    public StockResponseDto searchByCode(@RequestParam int key, @RequestParam int count, @RequestParam int index) {
        PageRequest pageRequest = PageRequest.of(index, count);
        StockResponseDto stock = stockService.findStocksByCode(key, pageRequest);
        return stock;
    }

    @GetMapping(value = "/stocks")
    public StockResponseDto findAll() {
//        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
//        System.out.println("이전시간" + beforeTime);
        StockResponseDto stocks = stockService.stockLists();

//        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
//        System.out.println("이후시간" + afterTime);
//        double secDiffTime = (afterTime - beforeTime)/1000.; //두 시간에 차 계산
//        System.out.println("시간차이(m) : "+secDiffTime);
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
