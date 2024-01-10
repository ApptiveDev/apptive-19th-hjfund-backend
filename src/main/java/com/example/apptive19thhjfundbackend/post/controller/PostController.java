package com.example.apptive19thhjfundbackend.post.controller;

import com.example.apptive19thhjfundbackend.post.data.dto.PostListResponseDto;
import com.example.apptive19thhjfundbackend.post.data.dto.PostResponseDto;
import com.example.apptive19thhjfundbackend.post.data.dto.PostSaveRequestDto;
import com.example.apptive19thhjfundbackend.post.data.dto.PostUpdateRequestDto;
import com.example.apptive19thhjfundbackend.post.service.PostService;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.utils.ApiUtils;
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
    public ResponseEntity<?> save(@AuthenticationPrincipal User user, @ModelAttribute PostSaveRequestDto requestDto) {
        postService.save(user, requestDto);

        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ResponseBody
    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},path = "/{id}")
    public ResponseEntity<?> update(@AuthenticationPrincipal User user, @PathVariable Long id, @ModelAttribute PostUpdateRequestDto requestDto)
    {
        postService.update(user, id, requestDto);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        PostResponseDto responseDto = postService.findById(id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDto);
        return ResponseEntity.ok(apiResult);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    @ResponseBody
    @DeleteMapping("/{id}")
    //본인이 쓴 글 여부 체크 코드 추가 예정
    public ResponseEntity<?> delete(@AuthenticationPrincipal User user, @PathVariable Long id)
    {
        postService.delete(user, id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll(@RequestParam int count, @RequestParam int index, @RequestParam String sortby)
    {
        Page<PostListResponseDto> responseDto = postService.findAllDesc(count, index, sortby);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDto);
        return ResponseEntity.ok(apiResult);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
