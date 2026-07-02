package com.alibaba.tqn.common.query.book;

import com.alibaba.tqn.shared.query.Query;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookQuery implements Query {

    private String name;
    private int pageSize;
    private int pageStart;
}
