package com.alibaba.tqn.domain.shared;

import java.util.List;
import com.alibaba.tqn.shared.exception.BizErrorCode;
import com.alibaba.tqn.shared.exception.BizException;
import com.alibaba.tqn.shared.query.Query;

public interface DomainService<Q extends Query, E extends Entity, R extends Repository<E, Q>> {

    default String getDomainName() {
        String serviceName = this.getClass().getSimpleName();
        int index = serviceName.indexOf("Service");
        return index > 0 ? serviceName.substring(0, index) : serviceName;
    }

    R getRepository();

    default long countByQuery(Q query) {
        return getRepository().count(query);
    }
    default List<E> listByQuery(Q query) {
        return getRepository().list(query);
    }
    default List<E> listByIds(List<Long> ids) {
        return getRepository().listByIds(ids);
    }

    default E get(long id) {
        E entity = getRepository().get(id);
        if (entity == null) {
            throw new BizException(BizErrorCode.ENTITY_NOT_EXIST,
                "'%s' not exist! id: %s", getDomainName(), id);
        }
        return entity;
    }

    default void create(E entity) {
        getRepository().save(entity);
    }

    default void modify(E entity) {
        getRepository().save(entity);
    }

    default int remove(long id) {
        return getRepository().delete(id);
    }
}
