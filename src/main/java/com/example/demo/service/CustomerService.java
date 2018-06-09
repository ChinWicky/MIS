package com.example.demo.service;

import com.example.demo.entity.Customer;
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
public interface CustomerService extends IService<Customer> {
    List<Customer> findAll();
    List<Customer> findPhone(String phone);
    Customer findById(int id);
}
