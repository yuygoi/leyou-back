package com.leyou.item.service.impl;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2019/7/22 0022 19:30
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }

    @Override
    public int addCategory(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int editCategory(Long id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int deleteCategory(Long id) {
        Category category = new Category();
        category.setId(id);
        return categoryMapper.delete(category);
    }

    @Override
    public List<String> selectCategoryNamesByIds(List<Long> ids) {
        List<Category> categories = categoryMapper.selectByIdList(ids);
        return categories.stream().map(category -> category.getName()).collect(Collectors.toList());
    }
}
