package com.alibaba.tqn.infra.dal.h2.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * Database Table: book
 */
public class BookDO {

    @Getter @Setter
    private Long id;

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

    @Getter @Setter
    private String name;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", creator=").append(creator);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", modifier=").append(modifier);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", deleted=").append(deleted);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}
