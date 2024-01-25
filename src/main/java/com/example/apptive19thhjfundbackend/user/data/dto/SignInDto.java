package com.example.apptive19thhjfundbackend.user.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {
    private String email;
    private String password;
    private boolean keep;

    public boolean getKeep() {
        return keep;
    }
    public void setKeep(boolean keep) {
        this.keep = keep;
    }
    public SignInDto() {
    }
}
