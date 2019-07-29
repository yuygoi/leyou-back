package com.leyou.item.service.api;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/7/23 0023 14:56
 */
public interface BrandService {
    PageResult<Brand> queryBrandsByPage(String key, Integer pageNum, Integer pageSize, String sortBy, Boolean desc);

    PageResult<Brand> queryBrandsByPage(String key, String sortBy, Boolean desc);

    int deleteBrandById(Long id);

    void addBrand(Brand brand, List<Long> cids);

    void editBrand(Brand brand);

    List<String> queryBrandByCid(Long cid);
}
