package com.alibaba.tqn.web.advice;

import com.alibaba.tqn.shared.exception.BizErrorCode;
import com.alibaba.tqn.shared.exception.BizException;
import com.alibaba.tqn.shared.query.Page;
import com.alibaba.tqn.shared.result.BizResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.validation.UnexpectedTypeException;
import jakarta.validation.ValidationException;

@Slf4j
@RestControllerAdvice(basePackages = "com.alibaba.tqn")
public class ApiAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getContainingClass().isAnnotationPresent(RestController.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (body == null) {
            return BizResult.ofSuccess();
        }
        if (body instanceof String) {
            return body;
        }
        if (body instanceof BizResult) {
            return body;
        }
        if (body instanceof Page<?> page) {
            return BizResult.ofSuccess(page);
        }
        return BizResult.ofSuccess(body);
    }

    @ExceptionHandler(BizException.class)
    public BizResult<?> handleBizException(BizException e) {
        Throwable cause = e.getCause();
        if (cause != null) {
            log.error("Api handle error! cause: ", cause);
        }
        log.error("Api handle error! code: {}, message: {}", e.getErrorCode(), e.getErrorMessage(), e);
        return BizResult.ofFailure(e);
    }

    @ExceptionHandler(BindException.class)
    public BizResult<?> handleBindException(BindException e) {
        log.error("Bind exception, message={}", e.getMessage());
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return BizResult.ofFailure(BizErrorCode.INVALID_PARAM, message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BizResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Method argument not valid, message={}", e.getMessage());
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return BizResult.ofFailure(BizErrorCode.INVALID_PARAM, message);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public BizResult<?> handleUnexpectedTypeException(UnexpectedTypeException e) {
        log.error("Unexpected type exception, message={}", e.getMessage());
        return BizResult.ofFailure(BizErrorCode.INVALID_PARAM, e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BizResult<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("Missing request parameter, message={}", e.getMessage());
        return BizResult.ofFailure(BizErrorCode.INVALID_PARAM, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BizResult<?> handleException(Exception e) {
        log.error("Api handle error!", e);
        return BizResult.ofFailure(BizErrorCode.INTERNAL_ERROR, "Internal error!");
    }
}
