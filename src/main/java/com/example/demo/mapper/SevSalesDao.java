package com.example.demo.mapper;

import com.example.demo.entity.SevSales;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wicky
 * @since 2018-05-16
 */
public interface SevSalesDao extends BaseMapper<SevSales> {
    List<SevSales> findAll();
}
