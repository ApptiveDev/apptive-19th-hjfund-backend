package com.example.apptive19thhjfundbackend.user.data.dao.impl;

import com.example.apptive19thhjfundbackend.user.data.entity.User;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import com.example.apptive19thhjfundbackend.user.data.dao.UserDao;
import com.example.apptive19thhjfundbackend.user.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;


@Component
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    @Autowired
    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User insertUser(User user) {
        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @Override
    public User selectUser(Long number) {
        User selectedUser = userRepository.getById(number);

        return selectedUser;
    }

    @Override
    public User updateUser(Long number, String nickName, String passWord) throws Exception {
        Optional<User> selectedUser = userRepository.findById(number);

        User updatedUser;
        if (selectedUser.isPresent()) {
            User user = selectedUser.get();

            user.setNickName(nickName);
            user.setPassWord(passWord);
            user.setUpdatedAt(LocalDateTime.now());

            updatedUser = userRepository.save(user);
        } else {
            throw new Exception();
        }

        return updatedUser;
    }

    @Override
    public void deleteUser(Long number) throws Exception{
        Optional<User> selectedUser = userRepository.findById(number);

        if (selectedUser.isPresent()) {
            User user = selectedUser.get();

            userRepository.delete(user);
        } else {
            throw new Exception();
        }
    }

}
