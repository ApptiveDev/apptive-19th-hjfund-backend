package com.example.apptive19thhjfundbackend.user.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    private String email;
    private String password;
    private String name;

    public SignInDto toSignInDto() {
        SignInDto signInDto = new SignInDto();
        signInDto.setEmail(this.email);
        signInDto.setPassword(this.password);
        signInDto.setKeep(false);
        return signInDto;
    }
}
