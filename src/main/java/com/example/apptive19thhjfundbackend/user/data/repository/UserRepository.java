package com.example.apptive19thhjfundbackend.user.data.repository;

import com.example.apptive19thhjfundbackend.user.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String uid);
    void deleteByUid(String uid);

    Page<User> findByRoles(String role, Pageable pageable);
}
