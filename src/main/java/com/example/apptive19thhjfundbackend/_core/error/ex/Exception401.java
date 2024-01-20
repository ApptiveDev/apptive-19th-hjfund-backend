package com.example.apptive19thhjfundbackend._core.error.ex;

import lombok.Getter;

@Getter
public class Exception401 extends RuntimeException{

    private boolean isBack;

    public Exception401(String msg, boolean isBack) {
        super(msg);
        this.isBack = isBack;
    }
}
