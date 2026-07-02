package com.alibaba.tqn.shared.exception;

public class BizException extends RuntimeException {

    private BizErrorCode errorCode;

    public BizException(String errorMessage) {
        this(BizErrorCode.BIZ_ERROR, errorMessage);
    }

    public BizException(BizErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public BizException(BizErrorCode errorCode, String errorMessage, Object... args) {
        super(String.format(errorMessage, args));
        this.errorCode = errorCode;
    }

    public BizErrorCode getErrorCode() { return errorCode; }
    public String getErrorMessage() { return super.getMessage(); }
}
