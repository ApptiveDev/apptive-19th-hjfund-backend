package com.example.apptive19thhjfundbackend.user.data.entity;

import com.example.apptive19thhjfundbackend.user.data.dto.StockDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;

    public StockDto toDto() {
        return StockDto.builder()
                .code(this.code)
                .name(this.name)
                .build();
    }

}
