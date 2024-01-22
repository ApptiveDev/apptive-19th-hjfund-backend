package com.example.apptive19thhjfundbackend.post.data.dto;

import com.example.apptive19thhjfundbackend.post.data.repository.LikeRepository;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeResponseDto {
    boolean state;

    @Builder
     public LikeResponseDto(boolean state) {
         this.state = state;
     }
}
