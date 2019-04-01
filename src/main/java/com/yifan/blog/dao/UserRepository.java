package com.yifan.blog.dao;

import com.yifan.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 定义函数要符合命名规范
    User findByUsernameAndPassword(String username, String password);

}
