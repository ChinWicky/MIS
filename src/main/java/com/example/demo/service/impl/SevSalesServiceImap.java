package com.example.demo.service.impl;

import com.example.demo.entity.SevSales;
import com.example.demo.mapper.SevSalesDao;
import com.example.demo.service.SevSalesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wicky
 * @since 2018-05-03
 */
@Service
public class SevSalesServiceImap extends ServiceImpl<SevSalesDao, SevSales> implements SevSalesService {
    @Autowired
    SevSalesDao sevSalesDao;

    @Override
    public List<SevSales> findAll() {
        return sevSalesDao.findAll();
    }
}
