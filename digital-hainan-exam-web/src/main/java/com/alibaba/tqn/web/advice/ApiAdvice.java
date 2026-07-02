package com.alibaba.tqn.web.advice;

import com.alibaba.tqn.shared.exception.BizException;
import com.alibaba.tqn.shared.result.BizResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiAdvice {

    @ExceptionHandler(BizException.class)
    public BizResult<?> handleBizException(BizException e) {
        log.warn("BizException: {}", e.getErrorMessage());
        return BizResult.ofFailure(e);
    }

    @ExceptionHandler(Exception.class)
    public BizResult<?> handleException(Exception e) {
        log.error("Unexpected error", e);
        return BizResult.ofFailure(
            new BizException("System error, please try again later"));
    }
}
