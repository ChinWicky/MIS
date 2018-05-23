package com.example.demo.service.impl;

import com.example.demo.entity.Pay;
import com.example.demo.mapper.PayDao;
import com.example.demo.service.PayService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wicky
 * @since 2018-05-16
 */
@Service
public class PayServiceImap extends ServiceImpl<PayDao, Pay> implements PayService {

}
