package com.example.demo.mapper;

import com.example.demo.entity.SecondaryCard;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wicky
 * @since 2018-05-15
 */
public interface SecondaryCardDao extends BaseMapper<SecondaryCard> {
    List<SecondaryCard> findId(int id);
    int updateCount(SecondaryCard secondaryCard);
}
