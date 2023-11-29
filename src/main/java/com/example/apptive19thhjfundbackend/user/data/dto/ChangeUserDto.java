package com.example.apptive19thhjfundbackend.user.data.dto;

public class ChangeUserDto {

    private Long number;
    private String nickName;
    private String passWord;

    public ChangeUserDto(Long number, String nickName, String passWord) {
        this.number = number;
        this.nickName = nickName;
        this.passWord = passWord;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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
