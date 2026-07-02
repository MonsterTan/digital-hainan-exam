package com.alibaba.tqn.common.application;

import java.util.Optional;

public class AppRequestContextUtils {

    private static final ThreadLocal<AppRequestContext> CONTEXT = new ThreadLocal<>();

    public static void init(AppRequestContext context) {
        CONTEXT.set(context);
    }

    public static void clear() {
        CONTEXT.remove();
    }

    public static CurrentUser getCurrentUser() {
        return Optional.ofNullable(CONTEXT.get())
            .map(AppRequestContext::getCurrentUser)
            .orElse(null);
    }

    public static String getCurrentUserEmail() {
        return Optional.ofNullable(CONTEXT.get())
            .map(AppRequestContext::getCurrentUser)
            .map(CurrentUser::getEmail)
            .orElse(null);
    }
}
