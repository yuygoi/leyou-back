package com.leyou.item.service.api;

import com.leyou.item.pojo.Category;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/7/22 0022 19:29
 */
public interface CategoryService {
    /**
     * 根据父id查询商品类别
     * @param pid
     * @return
     */
    List<Category> queryCategoryByPid(Long pid);

    int addCategory(Category category);

    int editCategory(Long id, String name);

    int deleteCategory(Long id);

    List<String> selectCategoryNamesByIds(List<Long> ids);
}
