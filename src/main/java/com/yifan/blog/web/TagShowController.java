package com.yifan.blog.web;

import com.yifan.blog.entity.Tag;
import com.yifan.blog.service.BlogService;
import com.yifan.blog.service.TagService;
import com.yifan.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    // 获取某个标签下的所有博客
    @GetMapping("/tags/{id}")
    public String type(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.listTagTop(10000); // size要足够大以显示所有标签
        if (id == -1) { // 初始状态，显示对应博客数最多的标签下的所有博客
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }

}
