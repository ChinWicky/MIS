package com.example.demo.service;

import com.example.demo.entity.SetMealSalesDetail;
import com.baomidou.mybatisplus.service.IService;
import com.example.demo.mapper.SetMealSalesDetailDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wicky
 * @since 2018-05-03
 */
public interface SetMealSalesDetailService extends IService<SetMealSalesDetail> {

        List<SetMealSalesDetail> findSetMeal(int id);
}
