package com.example.apptive19thhjfundbackend.post.controller;

import com.example.apptive19thhjfundbackend.post.data.dto.LikeResponseDto;
import com.example.apptive19thhjfundbackend.post.service.LikeService;
import com.example.apptive19thhjfundbackend.post.service.PostService;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class LikeController {
    private final LikeService likeService;
    @ResponseBody
    @PutMapping(path = "/{id}/like")
    public ResponseEntity<ApiUtils.ApiResult<LikeResponseDto>> like(@AuthenticationPrincipal User user, @PathVariable Long id)
    {
        LikeResponseDto responseDto = likeService.doLike(user, id);
        ApiUtils.ApiResult<LikeResponseDto> apiResult = ApiUtils.success(responseDto);
        return ResponseEntity.ok(apiResult);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
