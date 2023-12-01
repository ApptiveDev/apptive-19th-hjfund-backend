package com.example.apptive19thhjfundbackend.user.service.Impl;

import com.example.apptive19thhjfundbackend.user.config.security.JwtTokenProvider;

import com.example.apptive19thhjfundbackend.user.data.entity.Profile;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.user.data.repository.ProfileRepository;
import com.example.apptive19thhjfundbackend.user.data.repository.UserRepository;
import com.example.apptive19thhjfundbackend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProfileRepository profileRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User info() { // 사용자 정보 리턴
        UserDetails userDetails = contextHolder();
        User user = userInfo();
        Profile profile = user.getProfile();
        return user;
    }

    @Override
    public String changePW(String old, String password) throws Exception{ // username으로 찾아서 password만 바꾸고 다시 save
        UserDetails userDetails = contextHolder();
        if(!passwordEncoder.matches(old, userDetails.getPassword())) {
            throw new Exception();
        }

        User findUser = userRepository.getByUid(userDetails.getUsername());
        findUser.setPassWord(passwordEncoder.encode(password));

        User savedUser = userRepository.save(findUser);
        return savedUser.getPassword();
    }

    @Override // Multipart S3에 저장 및 String 경로 저장
    public User profile(String name, String bio, String phone) {
        User user = userInfo();
        Profile profile = user.getProfile();
        if (profile==null) {
            profile = Profile.builder()
                    .bio(bio)
                    .phone(phone)
                    .build();
        } else {
            profile.setBio(bio);
            profile.setPhone(phone);
        }
        Profile savedProfile = profileRepository.save(profile);
        user.setNickName(name);
        user.setProfile(savedProfile);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public String picture(MultipartFile file) {
        return " ";
    }

    @Override
    public void delete() {
        UserDetails userDetails = contextHolder();

        userRepository.deleteByUid(userDetails.getUsername());
    }

    private UserDetails contextHolder() { //로그인 된 사용자 정보
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        return userDetails;
    }

    private User userInfo() { //로그인 된 사용자 정보로 모든 유저 정보 얻기
        UserDetails userDetails = contextHolder();
        User user = userRepository.getByUid(userDetails.getUsername());
        return user;
    }
}
