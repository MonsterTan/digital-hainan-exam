package com.alibaba.tqn.infra.dal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.alibaba.tqn.infra.dal.h2")
public class MybatisConfig {
}
