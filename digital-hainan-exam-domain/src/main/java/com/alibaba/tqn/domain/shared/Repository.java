package com.alibaba.tqn.domain.shared;

import java.util.List;
import com.alibaba.tqn.shared.query.Query;

public interface Repository<E extends Entity, Q extends Query> {

    long count(Q query);

    List<E> list(Q query);

    default List<E> listByIds(List<Long> ids) {
        throw new UnsupportedOperationException();
    }

    E get(long id);

    void save(E entity);

    int delete(Long id);

    default void updateSelective(E entity) {
        save(entity);
    }
}
