package com.alibaba.tqn.common.application;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppRequestContext {
    private CurrentUser currentUser;
}
