package com.alibaba.tqn.infra.dal.h2.model;

import java.util.Date;
import lombok.Data;

@Data
public class BookDO {

    private Long id;
    private String creator;
    private Date gmtCreate;
    private String modifier;
    private Date gmtModified;
    private Boolean deleted;
    private String name;
}
