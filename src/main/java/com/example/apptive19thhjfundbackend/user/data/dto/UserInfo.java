package com.example.apptive19thhjfundbackend.user.data.dto;

import com.example.apptive19thhjfundbackend.user.data.entity.Profile;
import lombok.Getter;

@Getter
public class UserInfo {
    private Long id;
    private String uid; // email
    private String nickName;
    private String roles;
    private Profile profile;

    public UserInfo(Long id, String uid, String nickName, String roles, Profile profile) {
        this.id = id;
        this.uid = uid;
        this.nickName = nickName;
        this.roles = roles;
        this.profile = profile;
    }
}
