package com.example.demo.service.impl;

import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerDao;
import com.example.demo.service.CustomerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wicky
 * @since 2018-05-03
 */
@Service
public class CustomerServiceImap extends ServiceImpl<CustomerDao, Customer> implements CustomerService {

}
