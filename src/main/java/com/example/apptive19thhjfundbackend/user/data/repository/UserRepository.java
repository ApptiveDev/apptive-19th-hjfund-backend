package com.example.apptive19thhjfundbackend.user.data.repository;

import com.example.apptive19thhjfundbackend.user.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String uid);
}
