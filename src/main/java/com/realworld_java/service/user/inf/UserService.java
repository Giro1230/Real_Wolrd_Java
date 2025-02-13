package com.realworld_java.service.user.inf;


import com.realworld_java.controller.user.req.*;
import com.realworld_java.controller.user.res.*;

public interface UserService {
    UserRes register(UserReq data);
    UserRes login (UserReq data);
    UserRes getCurrentUser(Long userId);
    UserRes update(Long userId, UserReq data);
}
