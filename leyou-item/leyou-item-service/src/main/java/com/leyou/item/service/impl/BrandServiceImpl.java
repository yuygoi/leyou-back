package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.api.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author 叶俊晖
 * @date 2019/7/23 0023 14:56
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> queryBrandsByPage(String key, Integer pageNum, Integer pageSize, String sortBy, Boolean desc) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("name","%" + key + "%").orLike("letter" ,"%" + key + "%");
        }
        PageHelper.startPage(pageNum,pageSize);
        if (StringUtils.isNotBlank(sortBy) && Objects.nonNull(desc)){
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        List<Brand> brands = brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        return new PageResult<>(pageInfo.getTotal(),null,brands);
    }

    @Override
    public PageResult<Brand> queryBrandsByPage(String key, String sortBy, Boolean desc) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("name","%" + key + "%").orLike("letter" ,"%" + key + "%");
        }
        if (StringUtils.isNotBlank(sortBy) && Objects.nonNull(desc)){
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        List<Brand> brands = brandMapper.selectByExample(example);
        return new PageResult(new Long(brands.size()),null,brands);
    }

    @Override
    public int deleteBrandById(Long id) {
        Brand brand = new Brand();
        brand.setId(id);
        return brandMapper.deleteByPrimaryKey(brand);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void addBrand(Brand brand, List<Long> cids) {
        brandMapper.insertSelective(brand);
        cids.forEach(cid->{
            brandMapper.insertCategoryAndBrand(cid,brand.getId());
        });
    }

    @Override
    public void editBrand(Brand brand) {

    }

    @Override
    public List<String> queryBrandByCid(Long cid) {
        List<Brand> brands = brandMapper.selectByCid(cid);
        return brands.stream().map(brand -> brand.getName()).collect(Collectors.toList());
    }
}
