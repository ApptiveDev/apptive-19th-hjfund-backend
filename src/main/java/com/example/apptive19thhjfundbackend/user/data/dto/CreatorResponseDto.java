package com.example.apptive19thhjfundbackend.user.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreatorResponseDto {
    private List<UserInfo> userInfo;
    private int totalPages;
    private int numberOfElements;
    private int size;
    private int number;

    public CreatorResponseDto(List<UserInfo> userInfo, int totalPages, int numberOfElements, int size, int number) {
        this.userInfo = userInfo;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
        this.size = size;
        this.number = number;
    }
}
