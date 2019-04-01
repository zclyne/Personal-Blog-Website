package com.yifan.blog.service;

import com.yifan.blog.entity.User;

public interface UserService {

    User checkUser(String username, String password);

}
