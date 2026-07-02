package com.alibaba.tqn.domain.shared;

import java.util.Date;

public interface Entity {
    Long getId();
    void setId(Long id);
    String getCreator();
    void setCreator(String creator);
    void setCreateTime(Date createTime);
    String getModifier();
    void setModifier(String modifier);
    void setModifiedTime(Date modifiedTime);
}
