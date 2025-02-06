package com.realworld_java.user.service;

import com.realworld_java.user.controller.req.CurrentUserReq;
import com.realworld_java.user.controller.req.LoginUserReq;
import com.realworld_java.user.controller.req.RegisterUserReq;
import com.realworld_java.user.controller.req.UpdateUserReq;
import com.realworld_java.user.controller.res.CurrentUserRes;
import com.realworld_java.user.controller.res.LoginUserRes;
import com.realworld_java.user.controller.res.RegisterUserRes;
import com.realworld_java.user.controller.res.UpdatedUserRes;
import com.realworld_java.user.service.inf.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public RegisterUserRes register(RegisterUserReq data) {
        return null;
    }

    @Override
    public LoginUserRes login(LoginUserReq data) {
        return null;
    }

    @Override
    public CurrentUserRes getCurrentUser(CurrentUserReq data) {
        return null;
    }

    @Override
    public UpdatedUserRes update(String email, UpdateUserReq data) {
        return null;
    }
}
