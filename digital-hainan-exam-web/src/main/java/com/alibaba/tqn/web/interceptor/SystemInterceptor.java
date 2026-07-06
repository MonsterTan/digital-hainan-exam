package com.alibaba.tqn.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.alibaba.tqn.common.application.AppRequestContext;
import com.alibaba.tqn.common.application.AppRequestContextUtils;
import com.alibaba.tqn.common.application.CurrentUser;
import com.alibaba.tqn.common.utils.JwtUtils;
import com.alibaba.tqn.shared.result.BizResult;
import com.alibaba.tqn.shared.exception.BizErrorCode;
import com.alibaba.tqn.web.WebUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class SystemInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        try {
            String accessToken = request.getHeader("Access-Token");
            if (accessToken == null || accessToken.isBlank()) {
                WebUtils.writeApiResponse(response,
                    BizResult.ofFailure(BizErrorCode.TOKEN_INVALID, "Access token is required"));
                return false;
            }

            DecodedJWT jwt = jwtUtils.verifyToken(accessToken);
            if (jwt == null) {
                WebUtils.writeApiResponse(response,
                    BizResult.ofFailure(BizErrorCode.TOKEN_INVALID, "Invalid or expired token"));
                return false;
            }

            Long userId = jwtUtils.getUserId(jwt);
            String username = jwtUtils.getUsername(jwt);

            CurrentUser currentUser = CurrentUser.builder()
                .id(userId)
                .username(username)
                .build();
            AppRequestContextUtils.init(
                AppRequestContext.builder().currentUser(currentUser).build());

            MDC.put("userId", String.valueOf(userId));
            MDC.put("username", username);
        } catch (Exception e) {
            log.error("SystemInterceptor error", e);
            WebUtils.writeApiResponse(response,
                BizResult.ofFailure(BizErrorCode.TOKEN_INVALID, "Authentication failed"));
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) {
        AppRequestContextUtils.clear();
        MDC.remove("userId");
        MDC.remove("username");
    }
}
