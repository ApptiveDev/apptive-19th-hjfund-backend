package com.example.apptive19thhjfundbackend.post.data.dto;

import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String title;
    private String ticker;
    private String content;
    private String thumb;
    private List<MultipartFile> addedFiles;
    private List<String> removedFiles;
}
