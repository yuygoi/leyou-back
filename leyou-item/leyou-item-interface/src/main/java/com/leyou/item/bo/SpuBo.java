package com.leyou.item.bo;

import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/7/26 0026 14:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpuBo extends Spu {

    private String categoryName;

    private String brandName;

    private SpuDetail spuDetail;

    private List<Sku> skus;
}
