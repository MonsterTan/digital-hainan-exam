package com.alibaba.tqn.infra.dal.h2;

import java.util.List;

import com.alibaba.tqn.infra.dal.h2.model.UserDO;
import com.alibaba.tqn.infra.dal.h2.model.UserParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

    List<UserDO> selectByParam(UserParam param);
}
