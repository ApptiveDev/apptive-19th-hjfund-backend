package com.example.apptive19thhjfundbackend.user.service;

import com.example.apptive19thhjfundbackend.user.data.dto.UserDto;
import com.example.apptive19thhjfundbackend.user.data.dto.UserResponseDto;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User info();
    String changePW(String old, String password) throws Exception;
    User profile(String name, String bio, String phone);
    String picture(MultipartFile photo);
    void delete();

}
