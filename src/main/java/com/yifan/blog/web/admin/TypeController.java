package com.yifan.blog.web.admin;

import com.yifan.blog.entity.Type;
import com.yifan.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    // 访问分类列表
    // @PageableDefault指定分页参数
    // 按照id倒序排序
    @GetMapping("/types")
    public String types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    // 返回新增分类的页面
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    // 新增分类
    // @Valid表示要对type做校验
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) { // 已经存在该分类
            result.rejectValue("name", "nameError", "该分类已经存在");
        }
        if (result.hasErrors()) { // 校验未通过
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if (t == null) { // 保存失败
            attributes.addFlashAttribute("message", "新增失败");
        } else { // 保存成功
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    // 修改分类
    @GetMapping("/types/{id}/input")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    // 提交修改
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) { // 已经存在该分类
            result.rejectValue("name", "nameError", "该分类已经存在");
        }
        if (result.hasErrors()) { // 校验未通过
            return "admin/types-input";
        }
        Type t = typeService.updateType(id, type);
        if (t == null) { // 保存失败
            attributes.addFlashAttribute("message", "更新失败");
        } else { // 保存成功
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

}
