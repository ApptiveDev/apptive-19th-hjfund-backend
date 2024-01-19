package com.example.apptive19thhjfundbackend.stock.data.repository;

import com.example.apptive19thhjfundbackend.stock.data.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Page<Stock> findByNameStartingWith(String key, Pageable pageable);
    List<Stock> findByNameStartingWith(char key);

}
