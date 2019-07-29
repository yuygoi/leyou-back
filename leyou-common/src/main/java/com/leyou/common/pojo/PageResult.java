package com.leyou.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/7/23 0023 16:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private Long total;
    private Integer totalPage;
    private List<T> items;
}
