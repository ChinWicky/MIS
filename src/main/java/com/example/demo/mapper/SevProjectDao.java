
package com.example.demo.mapper;

import com.example.demo.entity.SevProject;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

        /**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wicky
 * @since 2018-03-26
 */
public interface SevProjectDao extends BaseMapper<SevProject> {
         List<SevProject> findAll();
         List<SevProject> findType(Integer id);
         List<SevProject> findByName(String name);
}