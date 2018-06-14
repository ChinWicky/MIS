package com.example.demo.service.impl;

import com.example.demo.entity.SecondaryCard;
import com.example.demo.mapper.SecondaryCardDao;
import com.example.demo.service.SecondaryCardService;
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
 * @since 2018-05-15
 */
@Service
public class SecondaryCardServiceImap extends ServiceImpl<SecondaryCardDao, SecondaryCard> implements SecondaryCardService {
    @Autowired
    SecondaryCardDao secondaryCardDao ;
    @Override
    public List<SecondaryCard> findSecondaryCardById(int id) {
        return secondaryCardDao.findId(id);
    }

    @Override
    public void updateCount(SecondaryCard secondaryCard) {
         secondaryCardDao.updateCount(secondaryCard);
    }
}
