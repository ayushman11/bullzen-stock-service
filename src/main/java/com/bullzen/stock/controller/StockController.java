package com.bullzen.stock.controller;

import com.bullzen.stock.dto.ApiResponse;
import com.bullzen.stock.dto.StockDto;
import com.bullzen.stock.entities.Stock;
import com.bullzen.stock.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/{stockId}")
    public ResponseEntity<ApiResponse<Stock>> getStockById(@PathVariable Long stockId) {
        Stock stock = stockService.getStockById(stockId);

        ApiResponse<Stock> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Stock fetched successfully",
                stock
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<List<StockDto>>> createStock(@Valid @RequestBody List<@Valid StockDto> listDto) {

        List<Stock> savedStock = stockService.saveAllStocks(listDto);
        List<StockDto> responseDTOs = savedStock.stream().map(StockDto::new).toList();

        ApiResponse<List<StockDto>> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Stocks created successfully",
                responseDTOs
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
