package com.example.apptive19thhjfundbackend.user.controller;

import com.example.apptive19thhjfundbackend.user.data.dto.ChangeUserDto;
import com.example.apptive19thhjfundbackend.user.data.dto.UserDto;
import com.example.apptive19thhjfundbackend.user.data.dto.UserInfo;
import com.example.apptive19thhjfundbackend.user.data.dto.UserResponseDto;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.user.data.repository.UserRepository;
import com.example.apptive19thhjfundbackend.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="") // 사용자 계정 정보 조회
    public ResponseEntity<UserInfo> userInfo() {
        UserInfo info = userService.info();
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }

    @PutMapping(value = "/password") //
    public ResponseEntity<String> changPassword(
            @RequestBody String old,
            @RequestBody String password) throws Exception{
        String updatedPass = userService.changePW(old, password);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @PutMapping(value = "/info") //
    public ResponseEntity<UserInfo> showProfile(
            @RequestBody String name,
            @RequestBody String bio,
            @RequestBody String phone) {
        UserInfo savedUser = userService.profile(name, bio, phone);
        return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }

    /*
    *  email
    */

    @PutMapping(value = "/picture") // 사진 변경
    public ResponseEntity<String> changePicture(
            @RequestParam MultipartFile file) {
        userService.picture(file);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }


    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> delete() {
        userService.delete();
        return ResponseEntity.status(HttpStatus.OK).body("삭제되었습니다.");
    }
}
