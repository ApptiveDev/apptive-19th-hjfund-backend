package com.example.apptive19thhjfundbackend.user.service.Impl;

import com.example.apptive19thhjfundbackend._core.error.ex.Exception401;
import com.example.apptive19thhjfundbackend.user.common.CommonResponse;
import com.example.apptive19thhjfundbackend.user.config.security.JwtTokenProvider;
import com.example.apptive19thhjfundbackend.user.data.dto.SignInResultDto;
import com.example.apptive19thhjfundbackend.user.data.dto.SignUpResultDto;
import com.example.apptive19thhjfundbackend.user.data.entity.Profile;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.user.data.repository.ProfileRepository;
import com.example.apptive19thhjfundbackend.user.data.repository.UserRepository;
import com.example.apptive19thhjfundbackend.user.service.SignService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class SignServiceImpl implements SignService {
    private final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    @Override
    public SignUpResultDto signUp(String id, String password, String name, String role) throws Exception {
        if (userRepository.getByUid(id)!=null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS");
        }

        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");
        User user;
        Profile profile = new Profile();
        profileRepository.save(profile);
        if (role.equalsIgnoreCase("admin")) {
            user = User.builder()
                    .uid(id)
                    .nickName(name)
                    .passWord(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .profile(profile)
                    .build();
        } else {
            user = User.builder()
                    .uid(id)
                    .nickName(name)
                    .passWord(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .profile(profile)
                    .build();
        }

        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignInResultDto();

        LOGGER.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        if(!savedUser.getNickName().isEmpty()) {
            LOGGER.info("[getSignUpResult] 정상 처리 완료");
            setSuccessResult(signUpResultDto);
        } else {
            LOGGER.info("[getSignUpResult] 실패 처리 완료");
            setFailResult(signUpResultDto);
        }
        return signUpResultDto;
    }

    @Override
    public SignInResultDto signIn(String id, String password) throws RuntimeException {
        LOGGER.info("[getSignInResult] signDataHandler로 회원 정보 요청");
        User user = userRepository.getByUid(id);
        LOGGER.info("[getSignInResult] Id : {}", id);

        LOGGER.info("[getSignInResult] 패스워드 비교 수행");
        if(!(user.getUid().equals(id))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS");
        }
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS");
        }
        LOGGER.info("[getSignInResult] 패스워드 일치");

        LOGGER.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUid()),
                        user.getRoles()))
                .build();

        LOGGER.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccessResult(signInResultDto);

        return signInResultDto;
    }

    private void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }

}
