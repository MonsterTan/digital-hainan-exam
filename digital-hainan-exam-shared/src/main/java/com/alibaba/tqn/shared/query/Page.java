package com.alibaba.tqn.shared.query;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {

    private long totalCount;
    private List<T> data;

    public static <T> Page<T> buildEmpty() {
        return build(0, Collections.emptyList());
    }

    public static <T> Page<T> build(long count, List<T> data) {
        return new Page<>(count, data);
    }
}
