package com.alibaba.tqn.infra.repository.book;

import java.util.List;

import com.alibaba.tqn.domain.book.Book;
import com.alibaba.tqn.infra.dal.h2.model.BookDO;
import com.alibaba.tqn.infra.repository.base.BaseConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseConverter.class)
public interface BookConverter {

    BookConverter INSTANCE = Mappers.getMapper(BookConverter.class);

    Book fromDO(BookDO data);

    List<Book> fromDO(List<BookDO> data);

    BookDO toDO(Book entity);
}
