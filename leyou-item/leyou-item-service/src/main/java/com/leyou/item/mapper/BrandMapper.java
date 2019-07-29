package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/7/23 0023 14:55
 */
public interface BrandMapper extends Mapper<Brand> {

    @Insert("insert into tb_category_brand" +
            "(category_id, brand_id) values(#{cid}, #{bid})")
    void insertCategoryAndBrand(@Param("cid")Long cid,@Param("bid") Long bid);

    @Select("select b.* " +
            "from " +
                "tb_brand b " +
                "inner join tb_category_brand cb " +
            "on cb.brand_id = b.id " +
            "where cb.category_id = #{cid}")
    List<Brand> selectByCid(Long cid);
}
