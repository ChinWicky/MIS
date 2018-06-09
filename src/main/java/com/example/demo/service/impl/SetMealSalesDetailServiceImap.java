package com.example.demo.service.impl;

import com.example.demo.entity.SetMealSalesDetail;
import com.example.demo.mapper.SetMealSalesDetailDao;
import com.example.demo.service.SetMealSalesDetailService;
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
public class SetMealSalesDetailServiceImap extends ServiceImpl<SetMealSalesDetailDao, SetMealSalesDetail> implements SetMealSalesDetailService {
    @Autowired
    SetMealSalesDetailDao setMealSalesDatailDao;

    @Override
    public List<SetMealSalesDetail> findSetMeal(int id) {
        return setMealSalesDatailDao.findSetMeal(id);
    }
}
