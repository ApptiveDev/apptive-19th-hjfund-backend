package com.example.apptive19thhjfundbackend.user.controller;

import com.example.apptive19thhjfundbackend.user.data.dto.StockDto;
import com.example.apptive19thhjfundbackend.user.data.repository.StockRepository;
import com.example.apptive19thhjfundbackend.user.service.SignService;
import com.example.apptive19thhjfundbackend.user.service.StockService;
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
        long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
        return stocks;
    }
    @GetMapping(value = "/stocks")
    public List<StockDto> findAll() {

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        List<StockDto> stocks =  stockService.stockLists();

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        double secDiffTime = (afterTime - beforeTime)/1000.; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
        return stocks;
    }

}
