package com.realworld_java.service.user.inf;


import com.realworld_java.controller.user.req.*;
import com.realworld_java.controller.user.res.*;

public interface UserService {
    RegisterUserRes register(RegisterUserReq data);
    LoginUserRes login (LoginUserReq data);
    CurrentUserRes getCurrentUser(CurrentUserReq data);
    UpdatedUserRes update(String email, UpdateUserReq data);
}
