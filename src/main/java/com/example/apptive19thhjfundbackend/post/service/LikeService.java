package com.example.apptive19thhjfundbackend.post.service;

import com.example.apptive19thhjfundbackend.post.data.dto.LikeResponseDto;
import com.example.apptive19thhjfundbackend.post.data.entity.Like;
import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import com.example.apptive19thhjfundbackend.post.data.repository.LikeRepository;
import com.example.apptive19thhjfundbackend.post.data.repository.PostRepository;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.utils.errors.exceptions.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    @Transactional
    public LikeResponseDto doLike(User user, Long id) {
        if (user == null) {
            new Exception404("로그인이 필요합니다.");
        }

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 게시글이 없습니다. id=" + id));

        Like like = likeRepository.findByUserAndPost(user, post).orElse(
                new Like(post, user)
        );
        boolean state = like.update();

        likeRepository.save(like);
        post.updateLikes(state);

        return new LikeResponseDto(state);
    }

    @Transactional
    public boolean findLikeByUserAndPost(User user, Post post) {
        if (user == null) {
            new Exception404("로그인이 필요합니다.");
        }

        Optional<Like> like = likeRepository.findByUserAndPost(user, post);
        if (like.isEmpty()) {
            return false;
        }
        return like.get().isState();
    }

    @Transactional
    public int countLikeByPost(Post post) {
        int likes = likeRepository.countAllByPost(post);

        return likes;
    }
}
