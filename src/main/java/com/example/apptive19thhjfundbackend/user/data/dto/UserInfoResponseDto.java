package com.example.apptive19thhjfundbackend.user.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoResponseDto {
    private String uid;
    private String nickName;
    private String bio;
    private String phone;
}
