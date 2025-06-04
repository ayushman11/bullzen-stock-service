package com.bullzen.stock.repository;

import com.bullzen.stock.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Boolean existsBySymbol(String symbol);
}
