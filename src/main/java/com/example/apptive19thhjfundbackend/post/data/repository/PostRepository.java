package com.example.apptive19thhjfundbackend.post.data.repository;

import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);
}
