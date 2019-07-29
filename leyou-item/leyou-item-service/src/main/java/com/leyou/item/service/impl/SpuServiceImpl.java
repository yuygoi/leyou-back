package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Spu;
import com.leyou.item.service.api.CategoryService;
import com.leyou.item.service.api.SpuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2019/7/26 0026 14:24
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;
    
    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageResult<SpuBo> querySpuBoByPage(String key, Boolean saleable, Integer page, Integer rows) {
        //拼接条件
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("title" ,"%"+ key + "%");
        }
        if (saleable != null){
            criteria.andEqualTo("saleable",saleable);
        }
        PageHelper.startPage(page,rows);
        List<Spu> spus = spuMapper.selectByExample(example);

        PageInfo<Spu> spuPageInfo = new PageInfo<>(spus);

        List<SpuBo> spuBos = spus.stream().map(spu -> {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu, spuBo);
            Brand brand = brandMapper.selectByPrimaryKey(spuBo.getBrandId());
            spuBo.setBrandName(brand.getName());
            List<String> categoryNames = categoryService.selectCategoryNamesByIds(
                    Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCategoryName(StringUtils.join(categoryNames, "/"));
            return spuBo;
        }).collect(Collectors.toList());

        return new PageResult<>(spuPageInfo.getTotal(),spuPageInfo.getPages(),spuBos);
    }
}
