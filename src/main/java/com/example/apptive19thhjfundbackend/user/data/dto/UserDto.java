package com.example.apptive19thhjfundbackend.user.data.dto;

public class UserDto {

    private String email;
    private String nickName;
    private String passWord;

    public UserDto(String email, String nickName, String passWord) {
        this.email = email;
        this.nickName = nickName;
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
