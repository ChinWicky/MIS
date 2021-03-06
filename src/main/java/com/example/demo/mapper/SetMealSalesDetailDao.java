package com.example.demo.mapper;

import com.example.demo.entity.SetMealSalesDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wicky
 * @since 2018-05-03
 */
public interface SetMealSalesDetailDao extends BaseMapper<SetMealSalesDetail> {

    List<SetMealSalesDetail> findSetMeal(int id);

}
