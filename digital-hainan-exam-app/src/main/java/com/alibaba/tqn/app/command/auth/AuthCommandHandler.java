package com.alibaba.tqn.app.command.auth;

import com.alibaba.tqn.common.utils.JwtUtils;
import com.alibaba.tqn.common.utils.PasswordUtils;
import com.alibaba.tqn.domain.user.User;
import com.alibaba.tqn.domain.user.service.UserService;
import com.alibaba.tqn.shared.exception.BizErrorCode;
import com.alibaba.tqn.shared.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthCommandHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    public String login(String username, String password) {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new BizException(BizErrorCode.BIZ_ERROR, "Invalid username or password");
        }

        if (user.getStatus() == null || user.getStatus() != 1) {
            throw new BizException(BizErrorCode.PERMISSION_DENIED, "Account has been disabled");
        }

        boolean matched = PasswordUtils.verify(password, user.getSalt(), user.getPassword());
        if (!matched) {
            throw new BizException(BizErrorCode.BIZ_ERROR, "Invalid username or password");
        }

        return jwtUtils.generateToken(user.getId(), user.getUsername());
    }
}
