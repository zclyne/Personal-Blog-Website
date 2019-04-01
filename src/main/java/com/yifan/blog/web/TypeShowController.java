package com.yifan.blog.web;

import com.yifan.blog.entity.Type;
import com.yifan.blog.service.BlogService;
import com.yifan.blog.service.TypeService;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    // 获取某个分类下的所有博客
    @GetMapping("/types/{id}")
    public String type(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model) {
        List<Type> types = typeService.listTypeTop(10000); // size足够大就能够显示所有分类
        if (id == -1) { // 默认情况为id = -1。表示选中文章数最多的分类
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id); // 当前选中的type在网页上与其他type样式不同
        return "types";
    }

}
