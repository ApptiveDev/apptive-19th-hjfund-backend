package com.example.apptive19thhjfundbackend.user.data.dto;

import com.example.apptive19thhjfundbackend.user.data.entity.Stock;
import lombok.*;

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
}
