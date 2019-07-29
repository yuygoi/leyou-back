package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.api.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/7/22 0022 19:31
 */
@RestController
@RequestMapping("/category/list")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父类型id查询分类
     * @param pid
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Category>> queryCategoryByPid(
            @RequestParam(value = "pid", defaultValue = "0")Long pid){
        if (pid == null || pid < 0){
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = categoryService.queryCategoryByPid(pid);
        if (CollectionUtils.isEmpty(categories)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }

    /**
     * 添加分类
     * @param category
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<String> addCategory(
            Category category){
        if (category.getParentId() == null || category.getParentId() < 0){
            return ResponseEntity.badRequest().build();
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改分类名称
     * @param id
     * @param name
     * @return
     */
    @PutMapping("/edit")
    public ResponseEntity<String> editCategory(
            @RequestParam("id") Long id,
            @RequestParam("name") String name){
        if (id == null || id < 0){
            return ResponseEntity.badRequest().build();
        }
        if (StringUtils.isNotBlank(name)){
            return ResponseEntity.badRequest().build();
        }
        categoryService.editCategory(id , name);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCategory(
            @RequestParam("id") Long id){
        if (id == null || id < 0){
            return ResponseEntity.badRequest().build();
        }
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
