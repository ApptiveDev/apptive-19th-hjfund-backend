package com.example.apptive19thhjfundbackend.post.data.dto;

import com.example.apptive19thhjfundbackend.file.entity.ContentFile;
import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String ticker;
    private String content;
    private String thumb;
    private Author author;
    private List<String> files;
    private int view;
    private int likes;
    private boolean like;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Getter
    public class Author {
        private Long id;
        private String name;
        private String profile;
        public Author(User user) {
            this.id = user.getId();
            this.name = user.getNickName();
            this.profile = user.getProfile().getPhoto();
        }
    }
    public PostResponseDto(Post entity, boolean like) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.ticker = entity.getTicker();
        this.content = entity.getContent();
        this.thumb = entity.getThumb();
        this.author = new Author(entity.getAuthor());
        if(entity.getContentFiles() != null) {
            List<ContentFile> contentFiles = entity.getContentFiles();
            Collections.sort(contentFiles);
            this.files =  contentFileToUrl(contentFiles);
        }
        this.view = entity.getViews();
        this.createdAt = entity.getCreateAt();
        this.updatedAt = entity.getUpdateAt();
        this.like = like;
        this.likes = entity.getLikes();
    }

    private List<String> contentFileToUrl(List<ContentFile> contentFiles) {
        return contentFiles.stream()
                    .map(c -> c.getUrl())
                    .collect(Collectors.toList());
    }

}