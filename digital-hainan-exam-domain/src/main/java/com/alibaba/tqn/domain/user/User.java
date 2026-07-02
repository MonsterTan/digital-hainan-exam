package com.alibaba.tqn.domain.user;

import java.util.Date;

import com.alibaba.tqn.domain.shared.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Entity {
    private Long id;
    private String username;
    private String password;
    private String salt;
    private Integer status;
    private String creator;
    private Date createTime;
    private String modifier;
    private Date modifiedTime;
    private Boolean deleted;
}
