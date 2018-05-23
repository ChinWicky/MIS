package com.example.demo.service.impl;

import com.example.demo.entity.SevSalesDetails;
import com.example.demo.mapper.SevSalesDetailsDao;
import com.example.demo.service.SevSalesDetailsService;
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
 * @since 2018-05-04
 */
@Service
public class SevSalesDetailsServiceImap extends ServiceImpl<SevSalesDetailsDao, SevSalesDetails> implements SevSalesDetailsService {

    @Autowired
    SevSalesDetailsDao sevSalesDetailsDao;
    @Override
    public List<SevSalesDetails> findWaiter(Integer id) {
        return sevSalesDetailsDao.findWaiter(id);
    }

    @Override
    public List<SevSalesDetails> findSalesId(Integer id) {
        return sevSalesDetailsDao.findSalesId(id);
    }

    @Override
    public List<SevSalesDetails> findAll() {
        return sevSalesDetailsDao.findAll();
    }

}
