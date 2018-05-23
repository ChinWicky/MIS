package com.example.demo.service;

import com.example.demo.entity.SevSales;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wicky
 * @since 2018-05-03
 */
public interface SevSalesService extends IService<SevSales> {
    List<SevSales> findAll();
}
