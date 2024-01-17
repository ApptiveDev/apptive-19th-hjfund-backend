package com.example.apptive19thhjfundbackend.user.data.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bio;
    private String phone;

    private String photo;

    public Profile(String bio, String phone, String photo) {
        this.bio = bio;
        this.phone = phone;
        this.photo = photo;
    }

}
