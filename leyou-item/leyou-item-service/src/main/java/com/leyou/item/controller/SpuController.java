package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Spu;
import com.leyou.item.service.api.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 叶俊晖
 * @date 2019/7/26 0026 14:22
 */
@Controller
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    @GetMapping("/page")
    public ResponseEntity<PageResult<SpuBo>> querySpuBoByPage(
            @RequestParam(value = "key" ,required = false)String key,
            @RequestParam(value = "saleable" ,required = false)Boolean saleable,
            @RequestParam(value = "page" ,defaultValue = "1")Integer page,
            @RequestParam(value = "rows" ,defaultValue = "5")Integer rows
    ){
        PageResult<SpuBo> result = spuService.querySpuBoByPage(key,saleable,page,rows);
        if (CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
