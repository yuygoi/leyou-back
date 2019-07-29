package com.leyou.item.controller;

import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.api.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/7/26 0026 10:16
 */
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/spec/group/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(
            @PathVariable(value = "cid") Long cid){
        System.err.println(cid);
        if (cid == null || cid < 0){
            return ResponseEntity.badRequest().build();
        }
        List<SpecGroup> specGroups = goodsService.queryGroupByCid(cid);
        if (CollectionUtils.isEmpty(specGroups)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(specGroups);
    }

    @GetMapping("/spec/params")
    public ResponseEntity<List<SpecParam>> querySpecParams(
            @RequestParam(value = "cid",required = false) Long cid,
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "generic",required = false) Boolean generic,
            @RequestParam(value = "searching",required = false) Boolean searching
    ){
        List<SpecParam> specParams = goodsService.queryParams(cid,gid,generic,searching);
        if (CollectionUtils.isEmpty(specParams)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(specParams);
    }

    @PostMapping("/goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo){
        goodsService.saveGoods(spuBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/goods")
    public ResponseEntity<Void> editGoods(@RequestBody SpuBo spuBo){
        goodsService.updateGoods(spuBo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(
            @PathVariable("spuId")Long spuId
    ){
        SpuDetail spuDetail = goodsService.querySpuDetailBySpuId(spuId);
        if (spuDetail == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spuDetail);
    }

    @GetMapping("/goods/sku/list")
    public ResponseEntity<List<Sku>> querySkusBySpuId(
            @RequestParam("skuId")Long skuId
    ){
        List<Sku> skus = goodsService.querySkusBySpuId(skuId);
        if (CollectionUtils.isEmpty(skus)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skus);
    }
}
