package com.yifan.blog.web;

import com.yifan.blog.entity.Comment;
import com.yifan.blog.entity.User;
import com.yifan.blog.service.BlogService;
import com.yifan.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    // 从properties文件中注入头像的路径
    @Value("${comment.avatar}")
    private String commentatorAvator;

    // 刷新评论区部分
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    // 接收提交的评论
    @PostMapping("/comments")
    public String postComment(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        User user = (User) session.getAttribute("user"); // 获取管理员账户，若当前用户不是管理员，则返回null
        if (user != null) { // 管理员已登录
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
//            comment.setNickname(user.getNickname());
        } else {
            comment.setAvatar(commentatorAvator);
        }
        comment.setBlog(blogService.getBlog(blogId));
        commentService.saveComment(comment);
        return "redirect:/comments/" + comment.getBlog().getId(); // 通过重定向刷新评论区
    }

}
