package com.example.apptive19thhjfundbackend.user.data.dto;

import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class CreatorPost {
    UserInfo userInfo;
    Page<Post> posts;

    public CreatorPost(UserInfo userInfo, Page<Post> posts) {
        this.userInfo = userInfo;
        this.posts = posts;
    }
}
