package com.example.demo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wicky
 * @since 2018-03-26
 */
public class Type extends Model<Type> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "typeid", type = IdType.AUTO)
    private Integer typeid;
    private String typename;


    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    protected Serializable pkVal() {
        return this.typeid;
    }

    @Override
    public String toString() {
        return "Type{" +
        "typeid=" + typeid +
        ", typename=" + typename +
        "}";
    }
}
