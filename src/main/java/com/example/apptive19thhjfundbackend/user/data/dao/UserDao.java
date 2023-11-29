package com.example.apptive19thhjfundbackend.user.data.dao;

import com.example.apptive19thhjfundbackend.user.data.entity.User;

public interface UserDao {
    User insertUser(User user);

    User selectUser(Long number);

    User updateUser(Long number, String nickName, String passWord) throws Exception;

    void deleteUser(Long number) throws Exception;
}
