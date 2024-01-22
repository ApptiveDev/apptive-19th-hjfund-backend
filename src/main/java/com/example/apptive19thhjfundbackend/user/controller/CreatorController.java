package com.example.apptive19thhjfundbackend.user.controller;

import com.example.apptive19thhjfundbackend.user.data.dto.CreatorPost;
import com.example.apptive19thhjfundbackend.user.data.dto.CreatorResponseDto;
import com.example.apptive19thhjfundbackend.user.data.dto.UserInfo;
import com.example.apptive19thhjfundbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CreatorController {

    private final UserService userService;

    @GetMapping("")
    public CreatorResponseDto allCreator(@RequestParam int count, @RequestParam int index) {
        PageRequest pageRequest = PageRequest.of(index, count);
        CreatorResponseDto userInfos = userService.allCreator(pageRequest);
        return userInfos;
    }

    @GetMapping("/{id}")
    public CreatorPost creatorPosts(@PathVariable Long id, @RequestParam int count, @RequestParam int index) {
        PageRequest pageRequest = PageRequest.of(index, count);
        CreatorPost creatorPost = userService.creatorPosts(id, pageRequest);
        return creatorPost;
    }
}
