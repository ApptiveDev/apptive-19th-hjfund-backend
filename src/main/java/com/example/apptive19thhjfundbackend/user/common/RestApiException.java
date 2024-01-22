package com.example.apptive19thhjfundbackend.user.common;



public class RestApiException extends RuntimeException {
    private final ErrorCode code;

    public RestApiException(ErrorCode code, String message){
        super(code.getMessage() + ": "+message);
        this.code = code ;
    }
    public RestApiException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public ErrorCode getCode(){
        return this.code;
    }
}
