package com.alibaba.tqn.infra.dal.h2;

import java.util.List;

import com.alibaba.tqn.infra.dal.h2.model.BookDO;
import com.alibaba.tqn.infra.dal.h2.model.BookParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookDAO {

    long countByParam(BookParam param);

    List<BookDO> selectByParam(BookParam param);

    BookDO selectByPrimaryKey(Long id);

    int insertSelective(BookDO record);

    int updateByPrimaryKeySelective(BookDO record);
}
