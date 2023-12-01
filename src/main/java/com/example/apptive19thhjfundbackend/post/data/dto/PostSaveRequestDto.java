package com.example.apptive19thhjfundbackend.post.data.dto;

import com.example.apptive19thhjfundbackend.post.data.entity.Post;
//import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Getter
public class PostSaveRequestDto {
    private String title;
    private String ticker;
    private String content;
    private String thumb;
    private List<MultipartFile> files;

    @Builder
    public PostSaveRequestDto(String title, String ticker, String content, String thumb, List<MultipartFile> files) {
        this.title = title;
        this.ticker = ticker;
        this.content = content;
        this.thumb = thumb;
        this.files = files;
    }

    public Post toEntity(User author) {
        return Post.builder()
                .title(title)
                .ticker(ticker)
                .content(content)
                .thumb(thumb)
                .author(author)
                .build();
    }

}
