package com.leyou.item.service.api;

import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.pojo.SpuDetail;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/7/26 0026 10:20
 */
public interface GoodsService {
    List<SpecGroup> queryGroupByCid(Long cid);

    List<SpecParam> queryParams(Long cid, Long gid, Boolean generic, Boolean searching);

    void saveGoods(SpuBo spuBo);

    void updateGoods(SpuBo spuBo);

    SpuDetail querySpuDetailBySpuId(Long spuId);

    List<Sku> querySkusBySpuId(Long skuId);
}
