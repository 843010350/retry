package com.wwm.retry.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private Integer code;

    private String message;
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(Integer code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }





}
