package com.example.demo.service.impl;

import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerDao;
import com.example.demo.service.CustomerService;
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
public class CustomerServiceImap extends ServiceImpl<CustomerDao, Customer> implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public List<Customer> findPhone(String phone) {
        return customerDao.findPhone(phone);
    }

    @Override
    public Customer findById(int id) {
        if (customerDao.findById(id).size()!=0)
            return customerDao.findById(id).get(0) ;
        else
            return null;

    }
}
