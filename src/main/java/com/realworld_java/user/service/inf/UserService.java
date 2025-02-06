package com.realworld_java.user.service.inf;

import com.realworld_java.user.controller.req.*;
import com.realworld_java.user.controller.res.*;

public interface UserService {
    RegisterUserRes register(RegisterUserReq data);
    LoginUserRes login (LoginUserReq data);
    CurrentUserRes getCurrentUser(CurrentUserReq data);
    UpdatedUserRes update(String email, UpdateUserReq data);
}
