package com.alibaba.tqn.shared.result;

import com.alibaba.tqn.shared.exception.BizErrorCode;
import com.alibaba.tqn.shared.exception.BizException;
import com.alibaba.tqn.shared.query.Page;
import lombok.Data;

@Data
public class BizResult<T> {

    private boolean success;
    private T data;
    private Long totalCount;
    private String errorCode;
    private String errorMessage;

    public static <T> BizResult<T> ofSuccess() {
        return ofSuccess(null, null);
    }

    public static <T> BizResult<T> ofSuccess(T data) {
        return ofSuccess(data, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> BizResult<T> ofSuccess(Page<T> page) {
        return ofSuccess((T) page.getData(), page.getTotalCount());
    }

    private static <T> BizResult<T> ofSuccess(T data, Long totalCount) {
        BizResult<T> result = new BizResult<>();
        result.success = true;
        result.data = data;
        result.totalCount = totalCount;
        return result;
    }

    public static BizResult<?> ofFailure(BizException e) {
        return ofFailure(e.getErrorCode(), e.getErrorMessage());
    }

    public static BizResult<?> ofFailure(BizErrorCode errorCode, String errorMessage) {
        BizResult<?> result = new BizResult<>();
        result.success = false;
        result.errorCode = errorCode.value();
        result.errorMessage = errorMessage;
        return result;
    }
}
