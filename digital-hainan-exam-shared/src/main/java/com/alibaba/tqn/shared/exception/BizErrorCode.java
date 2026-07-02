package com.alibaba.tqn.shared.exception;

public enum BizErrorCode {

    BIZ_ERROR("BIZ_ERROR"),
    ENTITY_NOT_EXIST("ENTITY_NOT_EXIST"),
    ENTITY_ALREADY_EXISTED("ENTITY_ALREADY_EXISTED"),
    INVALID_PARAM("INVALID_PARAM"),
    PERMISSION_DENIED("PERMISSION_DENIED"),
    TOKEN_VALID("TOKEN_VALID");

    private final String code;

    BizErrorCode(String code) {
        this.code = code;
    }

    public String value() {
        return code;
    }
}
