package com.example.demo.service;

import com.example.demo.entity.SevProject;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wicky
 * @since 2018-03-26
 */
public interface SevProjectService extends IService<SevProject> {
    List<SevProject> findAll();
    List<SevProject> findType(Integer id);
    List<SevProject> findByName(String name);
}