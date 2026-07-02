package com.alibaba.tqn.infra.dal.h2.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class UserDO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String salt;

    @Getter @Setter
    private Integer status;

    @Getter @Setter
    private String creator;

    @Getter @Setter
    private Date gmtCreate;

    @Getter @Setter
    private String modifier;

    @Getter @Setter
    private Date gmtModified;

    @Getter @Setter
    private Boolean deleted;
}
