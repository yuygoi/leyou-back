package com.leyou.item.service.impl;

import com.leyou.item.bo.SpuBo;
import com.leyou.item.mapper.*;
import com.leyou.item.pojo.*;
import com.leyou.item.service.api.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/7/26 0026 10:21
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public List<SpecGroup> queryGroupByCid(Long cid) {
        SpecGroup specGroup1 = new SpecGroup();
        specGroup1.setCid(cid);
        List<SpecGroup> specGroups = specGroupMapper.select(specGroup1);
        SpecParam specParam = new SpecParam();
        specGroups.forEach(specGroup -> {
            specParam.setGroupId(specGroup.getId());
            List<SpecParam> specParams = specParamMapper.select(specParam);
            specGroup.setParams(specParams);
        });
        return specGroups;
    }

    @Override
    public List<SpecParam> queryParams(Long cid, Long gid, Boolean generic, Boolean searching) {
        SpecParam specParam = new SpecParam();
        specParam.setCid(cid);
        specParam.setGroupId(gid);
        specParam.setGeneric(generic);
        specParam.setSearching(searching);
        return specParamMapper.select(specParam);
    }

    @Override
    @Transactional
    public void saveGoods(SpuBo spuBo) {
        //先新增spu
        spuBo.setId(null);
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date(System.currentTimeMillis()));
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        spuMapper.insertSelective(spuBo);

        //再去新增spuDetail
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        spuDetailMapper.insertSelective(spuDetail);

        saveSkuAndStock(spuBo);


    }

    public void saveSkuAndStock(SpuBo spuBo) {
        spuBo.getSkus().forEach(sku -> {
            //新增sku
            sku.setId(null);
            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date(System.currentTimeMillis()));
            sku.setLastUpdateTime(sku.getCreateTime());
            skuMapper.insertSelective(sku);
            //新增stock
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockMapper.insertSelective(stock);
        });
    }

    @Override
    @Transactional
    public void updateGoods(SpuBo spuBo) {
        //删除stock
        Sku sku = new Sku();
        sku.setSpuId(spuBo.getId());
        List<Sku> skus = skuMapper.select(sku);
        skus.forEach(sku1 -> {
            stockMapper.deleteByPrimaryKey(sku1.getId());
        });
        //删除sku
        skuMapper.delete(sku);
        //新增sku
        saveSkuAndStock(spuBo);
        //更新spu和spuDetail
        spuBo.setCreateTime(null);
        spuBo.setLastUpdateTime(new Date(System.currentTimeMillis()));
        spuBo.setValid(null);
        spuBo.setSaleable(null);
        spuMapper.updateByPrimaryKeySelective(spuBo);
        spuDetailMapper.updateByPrimaryKeySelective(spuBo.getSpuDetail());
    }

    @Override
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        return spuDetailMapper.selectByPrimaryKey(spuId);
    }

    @Override
    public List<Sku> querySkusBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skus = skuMapper.select(sku);
        skus.forEach(sku1 -> {
            Stock stock = stockMapper.selectByPrimaryKey(sku1.getId());
            sku1.setStock(stock.getStock());
        });
        return skus;
    }
}
