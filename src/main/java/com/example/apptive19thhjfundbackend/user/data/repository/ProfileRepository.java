package com.example.apptive19thhjfundbackend.user.data.repository;

import com.example.apptive19thhjfundbackend.user.data.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
