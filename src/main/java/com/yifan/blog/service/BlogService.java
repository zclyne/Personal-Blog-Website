package com.yifan.blog.service;

import com.yifan.blog.entity.Blog;
import com.yifan.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId, Pageable pageable);

    Page<Blog> listBlog(Pageable pageable, String query);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery); // blogQuery是通过查询条件封装的对象

    List<Blog> listRecommendBlogTop(Integer size);

    Map<String, List<Blog>> archiveBlog();

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    Long countBlog();

    void deleteBlog(Long id);

}
