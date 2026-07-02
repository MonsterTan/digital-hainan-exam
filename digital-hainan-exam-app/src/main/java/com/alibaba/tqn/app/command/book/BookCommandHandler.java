package com.alibaba.tqn.app.command.book;

import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.tqn.common.command.book.BookCreateCommand;
import com.alibaba.tqn.common.command.book.BookModifyCommand;
import com.alibaba.tqn.common.query.book.BookQuery;
import com.alibaba.tqn.common.vo.book.BookVO;
import com.alibaba.tqn.domain.book.Book;
import com.alibaba.tqn.domain.book.service.BookService;
import com.alibaba.tqn.shared.query.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BookCommandHandler {

    @Autowired
    private BookService bookService;

    private BookAssembler assembler = BookAssembler.INSTANCE;

    public BookVO get(Long id) {
        Book entity = bookService.get(id);
        return assembler.toVO(entity);
    }

    public List<BookVO> list(BookQuery query) {
        List<Book> entities = bookService.listByQuery(query);
        return entities.stream().map(assembler::toVO).collect(Collectors.toList());
    }

    public Page<BookVO> query(BookQuery query) {
        long count = bookService.countByQuery(query);
        if (count == 0) {
            return Page.buildEmpty();
        }
        List<Book> entities = bookService.listByQuery(query);
        List<BookVO> vos = entities.stream().map(assembler::toVO).collect(Collectors.toList());
        return Page.build(count, vos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(BookCreateCommand command) {
        Book entity = assembler.toEntity(command);
        bookService.create(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modify(BookModifyCommand command) {
        Book entity = bookService.get(command.getId());
        assembler.toEntity(command, entity);
        bookService.modify(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        bookService.remove(id);
    }
}
