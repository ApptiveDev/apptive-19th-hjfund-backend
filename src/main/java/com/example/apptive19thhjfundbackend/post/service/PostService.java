package com.example.apptive19thhjfundbackend.post.service;

import com.example.apptive19thhjfundbackend.S3.ImageS3Service;
import com.example.apptive19thhjfundbackend.file.dao.ContentFileRepository;
import com.example.apptive19thhjfundbackend.file.entity.ContentFile;
import com.example.apptive19thhjfundbackend.post.data.dto.*;
import com.example.apptive19thhjfundbackend.post.data.entity.Like;
import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import com.example.apptive19thhjfundbackend.post.data.repository.PostRepository;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.utils.errors.exceptions.Exception400;
import com.example.apptive19thhjfundbackend.utils.errors.exceptions.Exception401;
import com.example.apptive19thhjfundbackend.utils.errors.exceptions.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ImageS3Service imageS3Service;
    private final ContentFileRepository contentFileRepository;
    private final LikeService likeService;
    @Transactional
    public PostSaveResponseDto save(User author, PostSaveRequestDto requestDto)
    {
        if (author == null) {
            throw new Exception404("로그인이 필요합니다.");
        }
        Post post = postRepository.save(requestDto.toEntity(author));

        if (!requestDto.getFiles().isEmpty()) {
            List<ContentFile> fileEntities = new ArrayList<>();
            int order = 1;
            for(MultipartFile i : requestDto.getFiles()) {
                fileEntities.add(imageS3Service.uploadImage(i, order++).setPost(post));
            }
            contentFileRepository.saveAll(fileEntities);
        }
        return new PostSaveResponseDto(post.getId());
    }

    @Transactional
    public Long update(User user, Long id, PostUpdateRequestDto requestDto) {
        if (user == null) {
            throw new Exception404("로그인이 필요합니다.");
        }
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 게시글이 없습니다. id=" + id));
        if (!post.getAuthor().equals(user)) {
            throw new Exception401("해당 회원이 작성한 게시글이 아닙니다. id=" + id);
        }

        post.update(requestDto);
        List<ContentFile> contentFileList = post.getContentFiles();
        int order = contentFileList.size()+1;
        if (!requestDto.getAddedFiles().isEmpty()) {
            List<ContentFile> fileEntities = new ArrayList<>();
            for(MultipartFile i : requestDto.getAddedFiles()) {
                fileEntities.add(imageS3Service.uploadImage(i, order++).setPost(post));
            }
            contentFileRepository.saveAll(fileEntities);
        }
        if (!requestDto.getRemovedFiles().isEmpty()) {
            for (String i : requestDto.getRemovedFiles()) {
                if (contentFileList.contains(i)) {
                    imageS3Service.deleteImageFromS3(i);
                }else {
                    throw new Exception404("삭제하려는 파일이 게시물에 존재하지 않습니다.");
                }
            }
        }
        return id;
    }

    @Transactional
    public void delete (User user, Long id) {
        if (user == null) {
            throw new Exception404("로그인이 필요합니다.");
        }
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 게시글이 없습니다. id=" + id));
        if (!post.getAuthor().equals(user)) {
            throw new Exception401("해당 회원이 작성한 게시글이 아닙니다. id=" + id);
        }

        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(User user, Long id) {
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 게시글이 없습니다. id=" + id));
        boolean like = false;
        if (user != null) {
            like = likeService.findLikeByUserAndPost(user, entity);
        }

        entity.updateViews();
        return new PostResponseDto(entity, like);
    }

    @Transactional(readOnly = true)
    //정렬 기준에 따라 정렬하는 코드 추가 예정
    public Page<PostListResponseDto> findAllDesc(User user, int count, int index, String sortby) {
        Sort sort = null;
        if (sortby.toUpperCase().equals("POPULAR")) {
            sort = Sort.by("views").descending();
        }
        else if (sortby.toUpperCase().equals("RECENT")) {
            sort = Sort.by("createAt").descending();
        }
        else {
            throw new Exception400("잘못된 정렬 기준 입니다. 정렬 기준: " + sortby);
        }
        Pageable pageable = PageRequest.of(index, count, sort);

        if (user != null) {
            List<Post> likes = likeService.findLikeByUser(user);
            return postRepository.findAll(pageable)
                    .map(p -> PostListResponseDto.builder().entity(p).state(likes.contains(p)).build());
        }

        return postRepository.findAll(pageable)
                .map(p -> PostListResponseDto.builder().entity(p).state(false).build());

    }


}
