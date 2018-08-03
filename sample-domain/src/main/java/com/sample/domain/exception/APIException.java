package com.sample.domain.exception;

public class APIException extends RuntimeException {

    private static final long serialVersionUID = -6212475941372852475L;

    /**
     * コンストラクタ
     */
    public APIException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     */
    public APIException(Exception e) {
        super(e);
    }
}
