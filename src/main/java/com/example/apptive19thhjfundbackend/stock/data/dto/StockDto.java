package com.example.apptive19thhjfundbackend.stock.data.dto;

import com.example.apptive19thhjfundbackend.stock.data.entity.Stock;
import lombok.*;
import org.springframework.data.domain.Page;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    private String code;

    private String name;


    public Stock toEntity() {
        return Stock.builder()
                .code(this.code)
                .name(this.name)
                .build();
    }

    public Page<StockDto> toDtoList(Page<Stock> stocks) {
        return stocks.map(m -> StockDto.builder()
                .code(m.getCode())
                .name(m.getName())
                .build());
    }
}
