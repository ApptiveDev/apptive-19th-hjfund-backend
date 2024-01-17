package com.example.apptive19thhjfundbackend.user.service;

import com.example.apptive19thhjfundbackend.user.data.dto.CreatorPost;
import com.example.apptive19thhjfundbackend.user.data.dto.UserDto;
import com.example.apptive19thhjfundbackend.user.data.dto.UserInfo;
import com.example.apptive19thhjfundbackend.user.data.dto.UserResponseDto;
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

    Page<UserInfo> allCreator(Pageable pageable);
    CreatorPost creatorPosts(Long id, Pageable pageable);
}
