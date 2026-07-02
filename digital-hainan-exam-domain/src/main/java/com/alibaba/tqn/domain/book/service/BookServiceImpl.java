package com.alibaba.tqn.domain.book.service;

import com.alibaba.tqn.domain.book.Book;
import com.alibaba.tqn.domain.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public BookRepository getRepository() {
        return repository;
    }
}
