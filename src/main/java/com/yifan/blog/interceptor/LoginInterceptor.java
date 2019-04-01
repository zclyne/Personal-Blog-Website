package com.yifan.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 过滤器。只有在登录后才能访问/admin下的页面
public class LoginInterceptor extends HandlerInterceptorAdapter {

    // 预处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute("user") == null) { // user为空，未登录
            response.sendRedirect("/admin");
            return false;
        }

        return true;

    }
}
