package com.alibaba.tqn.app.command.book;

import com.alibaba.tqn.common.command.book.BookCreateCommand;
import com.alibaba.tqn.common.command.book.BookModifyCommand;
import com.alibaba.tqn.common.vo.book.BookVO;
import com.alibaba.tqn.domain.book.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookAssembler {

    BookAssembler INSTANCE = Mappers.getMapper(BookAssembler.class);

    Book toEntity(BookCreateCommand command);

    Book toEntity(BookModifyCommand command, @MappingTarget Book entity);

    BookVO toVO(Book entity);
}
