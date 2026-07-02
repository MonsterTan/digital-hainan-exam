package com.alibaba.tqn.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.alibaba.tqn.common.application.AppRequestContextUtils;
import com.alibaba.tqn.common.application.CurrentUser;
import com.alibaba.tqn.shared.exception.BizErrorCode;
import com.alibaba.tqn.shared.result.BizResult;
import com.alibaba.tqn.web.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class ApiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        CurrentUser user = AppRequestContextUtils.getCurrentUser();
        if (user == null) {
            WebUtils.writeApiResponse(response,
                BizResult.ofFailure(BizErrorCode.PERMISSION_DENIED, "No login!"));
            return false;
        }
        return true;
    }
}
