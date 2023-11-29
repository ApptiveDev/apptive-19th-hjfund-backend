package com.example.apptive19thhjfundbackend.user.service;

import com.example.apptive19thhjfundbackend.user.data.dto.UserDto;
import com.example.apptive19thhjfundbackend.user.data.dto.UserResponseDto;

public interface UserService {
    UserResponseDto getUser(Long number);

    UserResponseDto saveUser(UserDto userDto);

    UserResponseDto changeUser(Long number, String nickName, String passWord) throws Exception;

    void deleteUser(Long number) throws Exception;
}
