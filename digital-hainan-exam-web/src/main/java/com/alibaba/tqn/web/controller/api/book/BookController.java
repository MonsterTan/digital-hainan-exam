package com.alibaba.tqn.web.controller.api.book;

import java.util.List;
import jakarta.validation.Valid;

import com.alibaba.tqn.app.command.book.BookCommandHandler;
import com.alibaba.tqn.common.command.book.BookCreateCommand;
import com.alibaba.tqn.common.command.book.BookModifyCommand;
import com.alibaba.tqn.common.query.book.BookQuery;
import com.alibaba.tqn.common.vo.book.BookVO;
import com.alibaba.tqn.shared.query.Page;
import com.alibaba.tqn.shared.result.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book/")
public class BookController {

    @Autowired
    private BookCommandHandler commandHandler;

    @GetMapping("get")
    public BookVO get(Long id) {
        return commandHandler.get(id);
    }

    @PostMapping("list")
    public List<BookVO> list(@RequestBody @Valid BookQuery query) {
        return commandHandler.list(query);
    }

    @PostMapping("query")
    public Page<BookVO> query(@RequestBody @Valid BookQuery query) {
        return commandHandler.query(query);
    }

    @PostMapping("create")
    public BizResult<Void> create(@RequestBody @Valid BookCreateCommand command) {
        commandHandler.create(command);
        return BizResult.ofSuccess();
    }

    @PostMapping("modify")
    public BizResult<Void> modify(@RequestBody @Valid BookModifyCommand command) {
        commandHandler.modify(command);
        return BizResult.ofSuccess();
    }

    @GetMapping("delete")
    public BizResult<Void> delete(Long id) {
        commandHandler.delete(id);
        return BizResult.ofSuccess();
    }
}
