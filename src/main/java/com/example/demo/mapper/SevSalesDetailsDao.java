package com.example.demo.mapper;

import com.example.demo.entity.SevSalesDetails;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.Waiter;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wicky
 * @since 2018-05-04
 */
public interface SevSalesDetailsDao extends BaseMapper<SevSalesDetails> {

    List<SevSalesDetails> findWaiter(Integer id);
    List<SevSalesDetails> findSalesId(Integer id);
    List<SevSalesDetails> findAll();
}
