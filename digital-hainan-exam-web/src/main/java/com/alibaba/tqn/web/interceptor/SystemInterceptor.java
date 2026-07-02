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
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class SystemInterceptor implements HandlerInterceptor {

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
                    BizResult.ofFailure(BizErrorCode.TOKEN_VALID, "Access token is required"));
                return false;
            }

            DecodedJWT jwt = JwtUtils.verifyToken(accessToken);
            if (jwt == null) {
                WebUtils.writeApiResponse(response,
                    BizResult.ofFailure(BizErrorCode.TOKEN_VALID, "Token is invalid or expired, please login again"));
                return false;
            }

            Long userId = JwtUtils.getUserId(jwt);
            String username = JwtUtils.getUsername(jwt);

            CurrentUser currentUser = CurrentUser.builder()
                .id(userId)
                .username(username)
                .build();
            AppRequestContextUtils.init(
                AppRequestContext.builder().currentUser(currentUser).build());
        } catch (Exception e) {
            log.error("SystemInterceptor error", e);
            WebUtils.writeApiResponse(response,
                BizResult.ofFailure(BizErrorCode.TOKEN_VALID, "Authentication failed"));
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) {
        AppRequestContextUtils.clear();
    }
}
