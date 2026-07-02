package com.alibaba.tqn.domain.user;

public interface UserRepository {

    User findByUsername(String username);
}
