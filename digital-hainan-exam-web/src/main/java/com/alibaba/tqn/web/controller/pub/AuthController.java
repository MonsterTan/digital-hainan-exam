package com.alibaba.tqn.web.controller.pub;

import jakarta.validation.Valid;

import com.alibaba.tqn.app.command.auth.AuthCommandHandler;
import com.alibaba.tqn.shared.result.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class AuthController {

    @Autowired
    private AuthCommandHandler authCommandHandler;

    @PostMapping("/login")
    public BizResult<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        String token = authCommandHandler.login(request.getUsername(), request.getPassword());
        LoginResponse response = LoginResponse.builder()
            .token(token)
            .username(request.getUsername())
            .build();
        return BizResult.ofSuccess(response);
    }
}
