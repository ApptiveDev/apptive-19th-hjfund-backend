package com.example.apptive19thhjfundbackend.post.data.dto;

import lombok.Getter;

@Getter
public class PostSaveResponseDto {
    private String url;

    public PostSaveResponseDto(Long id) {
        this.url = "/api/report/" + id.toString();
    }
}
