package com.leyou.item.controller;

import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.common.utils.PinyinUtils;
import com.leyou.item.form.BrandForm;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.api.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/7/23 0023 14:57
 */
@RequestMapping("/brand")
@Controller
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<String>> queryBrandByCid(
            @PathVariable("cid")Long cid
    ){
        if (cid == null || cid < 0){
            return ResponseEntity.badRequest().build();
        }
        List<String> brandNames = brandService.queryBrandByCid(cid);
        if (CollectionUtils.isEmpty(brandNames)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brandNames);
    }

    @GetMapping("/list")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "1")Integer pageNum,
            @RequestParam(value = "rows", defaultValue = "5")Integer pageSize,
            @RequestParam(value = "sortBy", required = false)String sortBy,
            @RequestParam(value = "desc", required = false)Boolean desc
    ){
        if (pageSize < 0){
            //rows == ALL
            PageResult<Brand> result = brandService.queryBrandsByPage(key,sortBy,desc);
            return ResponseEntity.ok(result);
        }
        PageResult<Brand> result = brandService.queryBrandsByPage(key,pageNum,pageSize,sortBy,desc);
        if (CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(
            @RequestParam("id")Long id){
        if (id == null || id < 0){
            return ResponseEntity.badRequest().build();
        }
        brandService.deleteBrandById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids){
        if (StringUtils.isBlank(brand.getName())) {
            return ResponseEntity.badRequest().build();
        }
        if (!PinyinUtils.getPinYinHeadChar(brand.getName()).substring(0,1)
                .equalsIgnoreCase(brand.getLetter().toString())) {
            return ResponseEntity.badRequest().build();
        }
        brandService.addBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<String> edit(
            Brand brand){
        if (StringUtils.isBlank(brand.getName())) {
            return ResponseEntity.badRequest().build();
        }
        if (!PinyinUtils.getPinYinHeadChar(brand.getName()).substring(0,1)
                .equalsIgnoreCase(brand.getLetter().toString())) {
            return ResponseEntity.badRequest().build();
        }
        brandService.editBrand(brand);
        return ResponseEntity.ok().build();
    }
}
