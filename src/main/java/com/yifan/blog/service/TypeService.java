package com.yifan.blog.service;

import com.yifan.blog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    // 新增一个type
    Type saveType(Type type);

    // 查询
    Type getType(Long id);

    // 分页查询
    Page<Type> listType(Pageable pageable);

    // 获取所有的分类
    List<Type> listType();

    // 获取文章数从多到少排列的前size个type
    // 用于在首页显示
    List<Type> listTypeTop(Integer size);

    // 修改
    Type updateType(Long id, Type type);

    // 删除
    void deleteType(Long id);

    // 通过名称查询分类
    Type getTypeByName(String name);

}
