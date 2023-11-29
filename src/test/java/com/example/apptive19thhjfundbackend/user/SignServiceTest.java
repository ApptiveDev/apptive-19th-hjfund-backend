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
    String newPassword = "2222";
    String name = "jh";
    String role = "USER_ADMIN";
    String token = "";

    @Test
    void 회원가입() {
        //given
        //when
        SignUpResultDto signUpResultDto1 = signService.signUp(id, password, name, role);

        //then
        System.out.println("-------------------------------------");
        System.out.println(signUpResultDto1.print());
        System.out.println("-------------------------------------");
        Assertions.assertThat(0).isEqualTo(signUpResultDto1.getCode());
    }

    @Test
    void 로그인() {
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
        SignUpResultDto signUpResultDto = signService.signUp(id, password, name, role);
        //when
        SignInResultDto signInResultDto = signService.signIn(id, password);
        //then
        Assertions.assertThat(0).isEqualTo(signInResultDto.getCode());
        token = signInResultDto.getToken();
        System.out.println(token);
        //given
        if (token != null && jwtTokenProvider.validateToken(token)) {
            System.out.println("유효토큰 ---------------------------------------------------");
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication); // 추가
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();
        String password = userDetails.getPassword();
        System.out.println(username+  password);

    }

    @Test
    void 업데이트() { //ContextHolder때문에

    }
}
