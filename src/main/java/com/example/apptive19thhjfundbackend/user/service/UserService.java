package com.example.apptive19thhjfundbackend.user.service;

import com.example.apptive19thhjfundbackend.post.data.dto.PostListResponseDto;
import com.example.apptive19thhjfundbackend.user.data.dto.*;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserInfo info();
    String changePW(String old, String password) throws Exception;
    UserInfo profile(String name, String bio, String phone);
    String picture(MultipartFile photo) throws Exception;

    void delete();

    CreatorResponseDto allCreator(Pageable pageable);
    CreatorPost creatorPosts(Long id, Pageable pageable);

    Page<PostListResponseDto> findLikeByUser(Pageable pageable);
}
