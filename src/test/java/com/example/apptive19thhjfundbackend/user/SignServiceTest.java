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
    @Autowired
    UserRepository userRepository;
    @Autowired
    SignService signService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserDetailsService userDetailsService;

    String id = "wjdgh224";
    String password = "1111";
    String name = "jh";
    String role = "USER_ADMIN";
    String newPassword = "2222";
    String token = "";

    @Test
    void 회원가입() { // 중복 예외
        //given

        //when
        SignUpResultDto signUpResultDto = signService.signUp(id, password, name, role);
        //then
        Assertions.assertThat(0).isEqualTo(signUpResultDto.getCode());
    }

    @Test
    void 로그인() { // 없는 회원
        //given
        SignUpResultDto signUpResultDto = signService.signUp(id, password, name, role);
        //when
        SignInResultDto signInResultDto = signService.signIn(id, password);
        //then
        Assertions.assertThat(0).isEqualTo(signInResultDto.getCode());
        token = signInResultDto.getToken();
        System.out.println(token);
    }

    @Test
    void 토큰검사() {
        //given

        //when

        //then

    }

    @Test
    void 비밀번호번경() {
        //given

        //when

        //then
    }

    @Test
    void 회원탈퇴() {
        //given

        //when

        //then
    }

    @Test
    void 사용자정보() {
        //given

        //when

        //then
    }

    @Test
    void 프로필업데이트() {
        //given

        //when

        //then
    }

    @Test
    void 사진업데이트() {
        //given

        //when

        //then
    }
}
