package com.alibaba.tqn.shared.query;

import lombok.Data;

@Data
public class OrderBy {
    private String field;
    private boolean asc = true;
}
