package com.alibaba.tqn.infra.repository.book;

import java.util.List;

import com.alibaba.tqn.common.query.book.BookQuery;
import com.alibaba.tqn.domain.shared.EntityUtils;
import com.alibaba.tqn.domain.book.Book;
import com.alibaba.tqn.domain.book.BookRepository;
import com.alibaba.tqn.infra.dal.h2.BookDAO;
import com.alibaba.tqn.infra.dal.h2.model.BookDO;
import com.alibaba.tqn.infra.dal.h2.model.BookParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private BookDAO dao;

    private BookConverter converter = BookConverter.INSTANCE;

    @Override
    public long count(BookQuery query) {
        return dao.countByParam(buildParam(query));
    }

    @Override
    public List<Book> list(BookQuery query) {
        BookParam param = buildParam(query);
        if (query.getPageSize() > 0) {
            param.setPage(true);
            param.setPageStart(query.getPageStart());
            param.setPageSize(query.getPageSize());
        }
        param.appendOrderByClause(BookParam.OrderCondition.GMTCREATE, BookParam.SortType.DESC);
        return converter.fromDO(dao.selectByParam(param));
    }

    @Override
    public Book get(long id) {
        return converter.fromDO(dao.selectByPrimaryKey(id));
    }

    @Override
    public void save(Book entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            EntityUtils.initOperatingInfo(entity);
            dao.updateByPrimaryKeySelective(converter.toDO(entity));
        } else {
            EntityUtils.initCreateInfo(entity);
            BookDO record = converter.toDO(entity);
            dao.insertSelective(record);
            entity.setId(record.getId());
        }
    }

    @Override
    public int delete(Long id) {
        BookDO record = new BookDO();
        record.setId(id);
        record.setDeleted(Boolean.TRUE);
        return dao.updateByPrimaryKeySelective(record);
    }

    private BookParam buildParam(BookQuery query) {
        BookParam param = new BookParam();
        BookParam.Criteria criteria = param.createCriteria()
            .andDeletedEqualTo(Boolean.FALSE);
        if (query.getName() != null) {
            criteria.andNameLike("%" + query.getName() + "%");
        }
        return param;
    }
}
