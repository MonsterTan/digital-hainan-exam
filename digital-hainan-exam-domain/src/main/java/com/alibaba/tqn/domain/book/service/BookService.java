package com.alibaba.tqn.domain.book.service;

import com.alibaba.tqn.common.query.book.BookQuery;
import com.alibaba.tqn.domain.shared.DomainService;
import com.alibaba.tqn.domain.book.Book;
import com.alibaba.tqn.domain.book.BookRepository;

public interface BookService extends DomainService<BookQuery, Book, BookRepository> {

}
