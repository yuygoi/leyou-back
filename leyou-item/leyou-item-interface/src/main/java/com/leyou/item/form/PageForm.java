package com.leyou.item.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 叶俊晖
 * @date 2019/7/23 0023 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageForm {
    private String key;
    private Integer page;
    private Integer rows;
    private String sortBy;
    private Boolean desc;
}
