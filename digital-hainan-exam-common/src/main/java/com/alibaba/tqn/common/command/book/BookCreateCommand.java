package com.alibaba.tqn.common.command.book;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookCreateCommand {

    @NotBlank(message = "name cannot be blank")
    private String name;
}
