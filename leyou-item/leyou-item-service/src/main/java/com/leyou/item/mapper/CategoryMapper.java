package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 叶俊晖
 * @date 2019/7/22 0022 19:27
 */
public interface CategoryMapper extends Mapper<Category>,SelectByIdListMapper<Category,Long> {
}
