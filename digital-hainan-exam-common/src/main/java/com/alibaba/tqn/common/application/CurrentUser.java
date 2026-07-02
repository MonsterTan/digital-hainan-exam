package com.alibaba.tqn.common.application;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CurrentUser {
    private Long id;
    private String username;
    private String email;
}
