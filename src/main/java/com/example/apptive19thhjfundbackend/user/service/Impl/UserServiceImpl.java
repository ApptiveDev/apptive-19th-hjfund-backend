package com.example.apptive19thhjfundbackend.user.service.Impl;

import com.example.apptive19thhjfundbackend.user.data.dao.UserDao;
import com.example.apptive19thhjfundbackend.user.data.dto.UserDto;
import com.example.apptive19thhjfundbackend.user.data.dto.UserResponseDto;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserResponseDto getUser(Long number) {
        User user = userDao.selectUser(number);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setNumber(user.getNumber());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setNickName(user.getNickName());
        userResponseDto.setPassWord(user.getPassWord());

        return userResponseDto;
    }

    @Override
    public UserResponseDto saveUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setNickName(userDto.getNickName());
        user.setPassWord(userDto.getPassWord());
        user.setCreateAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userDao.insertUser(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setNumber(savedUser.getNumber());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setNickName(savedUser.getNickName());
        userResponseDto.setPassWord(savedUser.getPassWord());

        return userResponseDto;
    }

    @Override
    public UserResponseDto changeUser(Long number, String nickName, String passWord) throws Exception {
        User changedUser = userDao.updateUser(number, nickName, passWord);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setNumber(changedUser.getNumber());
        userResponseDto.setEmail(changedUser.getEmail());
        userResponseDto.setNickName(changedUser.getNickName());
        userResponseDto.setPassWord(changedUser.getPassWord());

        return userResponseDto;
    }

    @Override
    public void deleteUser(Long number) throws Exception{
        userDao.deleteUser(number);
    }
}
