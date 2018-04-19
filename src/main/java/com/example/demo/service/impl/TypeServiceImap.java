package com.example.demo.service.impl;

import com.example.demo.entity.Type;
import com.example.demo.mapper.TypeDao;
import com.example.demo.service.TypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wicky
 * @since 2018-03-26
 */
@Service
public class TypeServiceImap extends ServiceImpl<TypeDao, Type> implements TypeService {

}
