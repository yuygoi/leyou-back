package com.leyou.item.service.api;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;

/**
 * @author 叶俊晖
 * @date 2019/7/26 0026 14:23
 */
public interface SpuService {
    PageResult<SpuBo> querySpuBoByPage(String key, Boolean saleable, Integer page, Integer rows);
}
