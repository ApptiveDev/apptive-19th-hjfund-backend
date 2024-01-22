package com.example.apptive19thhjfundbackend.post.data.dto;


import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private String ticker;
    private String thumb;
    private Author author;
    private int view;
    private int likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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

    @Builder
    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.ticker = entity.getTicker();
        this.thumb = entity.getThumb();
        this.author = new Author(entity.getAuthor());
        this.view = entity.getViews();
        this.createdAt = entity.getCreateAt();
        this.updatedAt = entity.getUpdateAt();
        this.likes = entity.getLikes();
    }
}
