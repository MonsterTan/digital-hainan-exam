package com.alibaba.tqn.infra.repository.user;

import java.util.List;

import com.alibaba.tqn.domain.user.User;
import com.alibaba.tqn.domain.user.UserRepository;
import com.alibaba.tqn.infra.dal.h2.UserDAO;
import com.alibaba.tqn.infra.dal.h2.model.UserDO;
import com.alibaba.tqn.infra.dal.h2.model.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserDAO dao;

    private final UserConverter converter = UserConverter.INSTANCE;

    @Override
    public User findByUsername(String username) {
        UserParam param = new UserParam();
        param.createCriteria()
            .andUsernameEqualTo(username)
            .andDeletedEqualTo(Boolean.FALSE);
        List<UserDO> list = dao.selectByParam(param);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return converter.fromDO(list.get(0));
    }
}
