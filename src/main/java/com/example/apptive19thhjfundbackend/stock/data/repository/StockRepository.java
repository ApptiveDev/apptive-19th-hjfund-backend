package com.example.apptive19thhjfundbackend.stock.data.repository;

import com.example.apptive19thhjfundbackend.stock.data.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByNameStartingWith(String key);
    List<Stock> findByNameStartingWith(char key);

}