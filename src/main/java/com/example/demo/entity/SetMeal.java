package com.example.demo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wicky
 * @since 2018-05-03
 */
@TableName("set_meal")
public class SetMeal extends Model<SetMeal> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "set_meal_id", type = IdType.AUTO)
    private Integer setMealId;
    @TableField("set_meal_name")
    private String setMealName;
    @TableField("set_meal_price")
    private Double setMealPrice;


    public Integer getSetMealId() {
        return setMealId;
    }

    public void setSetMealId(Integer setMealId) {
        this.setMealId = setMealId;
    }

    public String getSetMealName() {
        return setMealName;
    }

    public void setSetMealName(String setMealName) {
        this.setMealName = setMealName;
    }

    public Double getSetMealPrice() {
        return setMealPrice;
    }

    public void setSetMealPrice(Double setMealPrice) {
        this.setMealPrice = setMealPrice;
    }

    @Override
    protected Serializable pkVal() {
        return this.setMealId;
    }

    @Override
    public String toString() {
        return "SetMeal{" +
        "setMealId=" + setMealId +
        ", setMealName=" + setMealName +
        ", setMealPrice=" + setMealPrice +
        "}";
    }
}
