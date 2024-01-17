package com.example.apptive19thhjfundbackend.user.service.Impl;


import com.example.apptive19thhjfundbackend.S3.ImageS3Service;

import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import com.example.apptive19thhjfundbackend.post.data.repository.PostRepository;
import com.example.apptive19thhjfundbackend.user.config.security.JwtTokenProvider;

import com.example.apptive19thhjfundbackend.user.data.dto.CreatorPost;
import com.example.apptive19thhjfundbackend.user.data.dto.UserInfo;
import com.example.apptive19thhjfundbackend.user.data.entity.Profile;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.user.data.repository.ProfileRepository;
import com.example.apptive19thhjfundbackend.user.data.repository.UserRepository;
import com.example.apptive19thhjfundbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;
    private final ImageS3Service imageS3Service;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserInfo info() { // 사용자 정보 리턴
        return userInfo().toUserInfo();
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
    public UserInfo profile(String name, String bio, String phone) {
        User user = userInfo();
        Profile profile = user.getProfile();
        profile.setBio(bio);
        profile.setPhone(phone);
        Profile savedProfile = profileRepository.save(profile);
        user.setNickName(name);
        user.setProfile(savedProfile);
        User savedUser = userRepository.save(user);
        return savedUser.toUserInfo();
    }

    @Override
    public String picture(MultipartFile file) throws Exception {
        String storedImagePath = imageS3Service.uploadImage(file);
        return storedImagePath;
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

    @Override
    public Page<UserInfo> allCreator(Pageable pageable) {
        Page<User> users = userRepository.findByRoles("ROLE_ADMIN", pageable);
        PageImpl<UserInfo> userInfos = new PageImpl<>(users.stream().map(user -> user.toUserInfo()).collect(Collectors.toList()));
        return userInfos;
    }

    @Override
    public CreatorPost creatorPosts(Long id, Pageable pageable) {
        User user = userRepository.getById(id);
        Page<Post> posts = postRepository.findByAuthorId(id, pageable);

        return new CreatorPost(user.toUserInfo(), posts);
    }
}
