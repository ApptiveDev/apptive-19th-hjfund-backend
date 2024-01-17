package com.example.apptive19thhjfundbackend.user.controller;

import com.example.apptive19thhjfundbackend.user.data.dto.SignInDto;
import com.example.apptive19thhjfundbackend.user.data.dto.SignInResultDto;
import com.example.apptive19thhjfundbackend.user.data.dto.SignUpDto;
import com.example.apptive19thhjfundbackend.user.data.dto.SignUpResultDto;
import com.example.apptive19thhjfundbackend.user.service.SignService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/auth")
public class SignController {
    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<SignInResultDto> signIn(
            HttpServletResponse response,
            @RequestBody SignInDto signInDto)
            throws RuntimeException {
        LOGGER.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", signInDto.getEmail());
        SignInResultDto signInResultDto = signService.signIn(signInDto.getEmail(), signInDto.getPassword());

        if (signInResultDto.getCode() == 0) {
            LOGGER.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", signInDto.getEmail(), signInResultDto.getToken());
        }
        response.setHeader("X-AUTH-TOKEN", signInResultDto.getToken());
        return ResponseEntity.status(HttpStatus.OK).body(signInResultDto);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<SignUpResultDto> signUp(
            @ModelAttribute SignUpDto signUpDto) {
        LOGGER.info("[signUp] 회원가입을 수행합니다. id : {}, pw : ****, name : {}, role : {}", signUpDto.getEmail(), signUpDto.getName(), signUpDto.getRole());
        SignUpResultDto signUpResultDto = signService.signUp(signUpDto.getEmail(), signUpDto.getPassword(), signUpDto.getName(), signUpDto.getRole());

        LOGGER.info("[signUp] 회원가입을 완료했습니다. id : {}", signUpDto.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(signUpResultDto);
    }

     /*
     *login/oauth
     */


    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    /*
    * renew
    * */

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
