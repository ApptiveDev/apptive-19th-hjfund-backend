package com.example.apptive19thhjfundbackend.user.controller;

import com.example.apptive19thhjfundbackend.user.data.dto.ChangeUserDto;
import com.example.apptive19thhjfundbackend.user.data.dto.UserDto;
import com.example.apptive19thhjfundbackend.user.data.dto.UserResponseDto;
import com.example.apptive19thhjfundbackend.user.data.repository.UserRepository;
import com.example.apptive19thhjfundbackend.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "GET 메서드", notes = "@RequestParam을 이용한 GET Method")
    @GetMapping()
    public ResponseEntity<UserResponseDto> getUser(
            @ApiParam(value = "인덱스", required = true) @RequestParam Long number) {
        UserResponseDto userResponseDto = userService.getUser(number);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @ApiOperation(value = "POST 메서드", notes = "@RequestBody를 이용한 POST Method")
    @PostMapping()
    public ResponseEntity<UserResponseDto> createUser(
            @ApiParam(value="이메일, 닉네임, 비밀번호", required = true) @RequestBody UserDto userDto) {
        UserResponseDto userResponseDto = userService.saveUser(userDto);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @ApiOperation(value = "PUT 메서드", notes = "@RequestBody를 이용한 PUT Method")
    @PutMapping()
    public ResponseEntity<UserResponseDto> changeUser(
            @ApiParam(value="인덱스, 닉네임, 비밀번호", required = true) @RequestBody ChangeUserDto changeUserDto) throws Exception {
        UserResponseDto userResponseDto = userService.changeUser(
                changeUserDto.getNumber(),
                changeUserDto.getNickName(),
                changeUserDto.getPassWord());

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @ApiOperation(value = "DELETE 메서드", notes = "@RequestParam을 이용한 DELETE Method")
    @DeleteMapping()
    public ResponseEntity<String> deleteUser(
            @ApiParam(value = "인덱스", required = true) @RequestParam Long number) throws Exception {
        userService.deleteUser(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
