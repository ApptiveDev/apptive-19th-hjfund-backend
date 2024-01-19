package com.example.apptive19thhjfundbackend.user;

import com.example.apptive19thhjfundbackend.user.config.security.JwtTokenProvider;
import com.example.apptive19thhjfundbackend.user.data.dto.SignInResultDto;
import com.example.apptive19thhjfundbackend.user.data.dto.SignUpResultDto;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.user.data.repository.UserRepository;
import com.example.apptive19thhjfundbackend.user.service.SignService;
import com.example.apptive19thhjfundbackend.user.service.UserDetailsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@SpringBootTest
@Transactional
class SignServiceTest {
    /*
    * 회원가입
    * 로그인
    * 토큰 검사
    * 비밀번호 변경
    * 회원 탈퇴
    * 프로필 업데이트
    * 사진 업데이트
    * 사용자 정보 얻기(프로필, 아이디 ...)
    * */
}
