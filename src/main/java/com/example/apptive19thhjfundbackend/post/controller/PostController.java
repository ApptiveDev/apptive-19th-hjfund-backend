package com.example.apptive19thhjfundbackend.post.controller;

import com.example.apptive19thhjfundbackend.post.data.dto.*;
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
    public ResponseEntity<ApiUtils.ApiResult<PostSaveResponseDto>> save(@AuthenticationPrincipal User user, @ModelAttribute PostSaveRequestDto requestDto) {
        PostSaveResponseDto responseDto = postService.save(user, requestDto);

        ApiUtils.ApiResult<PostSaveResponseDto> apiResult = ApiUtils.success(responseDto);
        return ResponseEntity.ok(apiResult);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ResponseBody
    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},path = "/{id}")
    public ResponseEntity<ApiUtils.ApiResult<?>> update(@AuthenticationPrincipal User user, @PathVariable Long id, @ModelAttribute PostUpdateRequestDto requestDto)
    {
        postService.update(user, id, requestDto);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiUtils.ApiResult<PostResponseDto>> findById(@AuthenticationPrincipal User user, @PathVariable Long id)
    {
        PostResponseDto responseDto = postService.findById(user, id);
        ApiUtils.ApiResult<PostResponseDto> apiResult = ApiUtils.success(responseDto);
        return ResponseEntity.ok(apiResult);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    @ResponseBody
    @DeleteMapping("/{id}")
    //본인이 쓴 글 여부 체크 코드 추가 예정
    public ResponseEntity<ApiUtils.ApiResult<?>> delete(@AuthenticationPrincipal User user, @PathVariable Long id)
    {
        postService.delete(user, id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/")
    public ResponseEntity<ApiUtils.ApiResult<Page<PostListResponseDto>>> findAll(@AuthenticationPrincipal User user, @RequestParam int count, @RequestParam int index, @RequestParam String sortby)
    {
        Page<PostListResponseDto> responseDto = postService.findAllDesc(user, count, index, sortby);
        ApiUtils.ApiResult<Page<PostListResponseDto>> apiResult = ApiUtils.success(responseDto);
        return ResponseEntity.ok(apiResult);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


}
