package com.alibaba.tqn.common.command.book;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookModifyCommand {

    @NotNull
    private Long id;

    private String name;
}
