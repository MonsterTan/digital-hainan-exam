package com.alibaba.tqn.domain.book;

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
public class Book implements Entity {

    private Long id;
    private String creator;
    private Date createTime;
    private String modifier;
    private Date modifiedTime;
    private Boolean deleted;

    private String name;
}
