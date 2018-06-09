package com.example.demo.mapper;

import com.example.demo.entity.Customer;
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
public interface CustomerDao extends BaseMapper<Customer> {
    List<Customer> findAll();
    List<Customer> findPhone(String phone);
    List<Customer> findById(int id);
}
