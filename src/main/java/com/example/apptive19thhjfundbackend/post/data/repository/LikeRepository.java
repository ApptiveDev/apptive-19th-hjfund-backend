package com.example.apptive19thhjfundbackend.post.data.repository;

import com.example.apptive19thhjfundbackend.post.data.entity.Like;
import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByUser(User user);
    int countAllByPost(Post post);
    Optional<Like> findByUserAndPost(User user, Post post);
}
