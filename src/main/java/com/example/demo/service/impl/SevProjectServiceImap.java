package com.example.demo.service.impl;

import com.example.demo.entity.SevProject;
import com.example.demo.mapper.SevProjectDao;
import com.example.demo.service.SevProjectService;
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
 * @since 2018-03-26
 */
@Service
public class SevProjectServiceImap extends ServiceImpl<SevProjectDao, SevProject> implements SevProjectService {

    @Autowired
    SevProjectDao sevProjectDao;

    public List<SevProject> findAll(){
        return sevProjectDao.findAll();
   }

    public List<SevProject> findType(Integer id){
        return sevProjectDao.findType(id);
    }

    public  List<SevProject> findByName(String name){ return sevProjectDao.findByName(name); }


}