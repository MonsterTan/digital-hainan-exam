package com.alibaba.tqn.infra.repository.user;

import com.alibaba.tqn.domain.user.User;
import com.alibaba.tqn.infra.dal.h2.model.UserDO;
import com.alibaba.tqn.infra.repository.base.BaseConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseConverter.class)
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(source = "gmtCreate", target = "createTime")
    @Mapping(source = "gmtModified", target = "modifiedTime")
    User fromDO(UserDO data);
}
