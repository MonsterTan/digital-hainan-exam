package com.alibaba.tqn.shared.query;

import java.io.Serializable;
import java.util.List;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractPagedQuery implements Query, Serializable {

    protected boolean page = true;

    @Min(value = 1, message = "min 'pageNo' value is '1'!")
    protected int pageNo = 1;

    @Max(value = 100, message = "max 'pageSize' value is '100'!")
    protected int pageSize = 20;

    protected List<OrderBy> orderBys;

    public int getPageIndex() {
        return (pageNo - 1) * this.pageSize;
    }
}
