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
        userResponseDto.setId(user.getId());
        userResponseDto.setUid(user.getUid());
        userResponseDto.setNickName(user.getNickName());
        userResponseDto.setRole(user.getRoles().get(0));

        return userResponseDto;
    }

    @Override
    public UserResponseDto saveUser(UserDto userDto) {
        User user = new User();
        user.setUid(userDto.getUid());
        user.setNickName(userDto.getNickName());
        user.setPassWord(userDto.getPassWord());
        user.setCreateAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userDao.insertUser(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setUid(savedUser.getUid());
        userResponseDto.setNickName(savedUser.getNickName());
        userResponseDto.setRole(savedUser.getRoles().get(0));

        return userResponseDto;
    }

    @Override
    public UserResponseDto changeUser(Long number, String nickName, String passWord) throws Exception {
        User changedUser = userDao.updateUser(number, nickName, passWord);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(changedUser.getId());
        userResponseDto.setUid(changedUser.getUid());
        userResponseDto.setNickName(changedUser.getNickName());
        userResponseDto.setRole(changedUser.getRoles().get(0));

        return userResponseDto;
    }

    @Override
    public void deleteUser(Long number) throws Exception{
        userDao.deleteUser(number);
    }
}
