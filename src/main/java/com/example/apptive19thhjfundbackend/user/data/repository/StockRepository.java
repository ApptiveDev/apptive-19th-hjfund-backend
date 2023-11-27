package com.example.apptive19thhjfundbackend.user.data.repository;

import com.example.apptive19thhjfundbackend.user.data.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByNameStartingWith(String key);
}
