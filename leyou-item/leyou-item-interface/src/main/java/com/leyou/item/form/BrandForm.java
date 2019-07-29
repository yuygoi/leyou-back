package com.leyou.item.form;

import com.leyou.item.pojo.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/7/23 0023 21:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandForm extends Brand{
    private List<Long> cids;
}
