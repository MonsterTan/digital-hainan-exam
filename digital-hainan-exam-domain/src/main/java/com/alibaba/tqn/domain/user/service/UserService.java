package com.alibaba.tqn.domain.user.service;

import com.alibaba.tqn.domain.user.User;

public interface UserService {


    User findByUsername(String username);
}
