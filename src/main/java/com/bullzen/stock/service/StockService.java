package com.bullzen.stock.service;

import com.bullzen.stock.dto.StockDto;
import com.bullzen.stock.entities.Stock;
import com.bullzen.stock.exception.StockAlreadyExistsException;
import com.bullzen.stock.exception.StockNotFoundException;
import com.bullzen.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public void saveStock(Stock stock) {
        stockRepository.save(stock);
    }

    public Stock getStockById(Long stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(() -> new StockNotFoundException("Stock not found with ID: " + stockId));
    }

    public Stock saveStock(StockDto dto) {

        if(stockRepository.existsBySymbol(dto.getSymbol())) {
            throw new StockAlreadyExistsException("Stock with symbol " + dto.getSymbol() + " already exists");
        }

        Stock newStock = Stock.builder()
                .symbol(dto.getSymbol())
                .name(dto.getName())
                .currentPrice(dto.getCurrentPrice())
                .volume(dto.getVolume())
                .build();

        return stockRepository.save(newStock);
    }

    public List<Stock> saveAllStocks(List<StockDto> listDtos) {
        List<Stock> toSave = new ArrayList<>();

        for (StockDto dto : listDtos) {
            if (!stockRepository.existsBySymbol(dto.getSymbol())) {
                Stock stock = Stock.builder()
                        .symbol(dto.getSymbol())
                        .name(dto.getName())
                        .currentPrice(dto.getCurrentPrice())
                        .volume(dto.getVolume())
                        .build();
                toSave.add(stock);
            }
        }

        return stockRepository.saveAll(toSave);
    }
}
