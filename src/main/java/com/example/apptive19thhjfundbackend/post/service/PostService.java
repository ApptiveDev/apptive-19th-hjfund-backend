package com.example.apptive19thhjfundbackend.post.service;

import com.example.apptive19thhjfundbackend.S3.ImageS3Service;
import com.example.apptive19thhjfundbackend.file.dao.ContentFileRepository;
import com.example.apptive19thhjfundbackend.file.entity.ContentFile;
import com.example.apptive19thhjfundbackend.post.data.dto.PostListResponseDto;
import com.example.apptive19thhjfundbackend.post.data.dto.PostResponseDto;
import com.example.apptive19thhjfundbackend.post.data.dto.PostSaveRequestDto;
import com.example.apptive19thhjfundbackend.post.data.dto.PostUpdateRequestDto;
import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import com.example.apptive19thhjfundbackend.post.data.repository.PostRepository;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
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

    @Transactional
    public void save(User author, PostSaveRequestDto requestDto) throws Exception
    {
        Post post = postRepository.save(requestDto.toEntity(author));

        if (!requestDto.getFiles().isEmpty()) {
            List<ContentFile> fileEntities = new ArrayList<>();
            int order = 1;
            for(MultipartFile i : requestDto.getFiles()) {
                fileEntities.add(imageS3Service.uploadImage(i, order++).setPost(post));
            }
            contentFileRepository.saveAll(fileEntities);
        }
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

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
                    throw new Exception("삭제하려는 파일이 게시물에 존재하지 않습니다.");
                }
            }
        }
        return id;
    }

    @Transactional
    public void delete (Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostResponseDto(entity);
    }

    @Transactional(readOnly = true)
    //정렬 기준에 따라 정렬하는 코드 추가 예정
    public Page<PostListResponseDto> findAllDesc(int count, int index, String sortby) {
        Sort sort = Sort.by("createAt").descending();
        Pageable pageable = PageRequest.of(index, count, sort);

        return postRepository.findAll(pageable)
                .map(p -> PostListResponseDto.builder().entity(p).build());
    }
}
