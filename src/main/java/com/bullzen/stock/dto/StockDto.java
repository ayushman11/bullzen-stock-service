package com.bullzen.stock.dto;

import com.bullzen.stock.entities.Stock;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    @NotBlank(message = "Symbol is required")
    private String symbol;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    private Double currentPrice;

    @NotNull(message = "Volume is required")
    private Long volume;

    public StockDto(Stock stock) {
        this.symbol = stock.getSymbol();
        this.name = stock.getName();
        this.currentPrice = stock.getCurrentPrice();
        this.volume = stock.getVolume();
    }
}
