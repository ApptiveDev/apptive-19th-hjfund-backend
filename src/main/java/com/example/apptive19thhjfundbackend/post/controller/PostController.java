package com.example.apptive19thhjfundbackend.post.controller;

import com.example.apptive19thhjfundbackend.post.data.dto.PostListResponseDto;
import com.example.apptive19thhjfundbackend.post.data.dto.PostResponseDto;
import com.example.apptive19thhjfundbackend.post.data.dto.PostSaveRequestDto;
import com.example.apptive19thhjfundbackend.post.data.dto.PostUpdateRequestDto;
import com.example.apptive19thhjfundbackend.post.service.PostService;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class PostController {

    private final PostService postService;
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, path = "/")
    public ResponseEntity<?> save(@AuthenticationPrincipal User user, @ModelAttribute PostSaveRequestDto requestDto) throws Exception{
        postService.save(user, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ResponseBody
    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},path = "/{id}")
    //본인이 쓴 글 여부 체크 코드 추가 예정
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute PostUpdateRequestDto requestDto) throws Exception
    {
        postService.update(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        PostResponseDto responseDto = postService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    @ResponseBody
    @DeleteMapping("/{id}")
    //본인이 쓴 글 여부 체크 코드 추가 예정
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        postService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll(@RequestParam int count, @RequestParam int index, @RequestParam String sortby)
    {
        Page<PostListResponseDto> responseDto = postService.findAllDesc(count, index, sortby);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
