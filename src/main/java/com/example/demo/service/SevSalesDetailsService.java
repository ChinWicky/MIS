package com.example.demo.service;

import com.example.demo.entity.SevSalesDetails;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wicky
 * @since 2018-05-04
 */
public interface SevSalesDetailsService extends IService<SevSalesDetails> {
    List<SevSalesDetails> findWaiter(Integer id);
    List<SevSalesDetails> findSalesId(Integer id);
    List<SevSalesDetails> findAll();
}
