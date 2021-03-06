package com.example.demo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
 * @since 2018-06-02
 */
@TableName("set_meal_sales")
public class SetMealSales extends Model<SetMealSales> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "set_meal_sale_id", type = IdType.AUTO)
    private Integer setMealSaleId;
    @TableField("customer_id")
    private Integer customerId;
    @TableField("set_meal_id")
    private Integer setMealId;
    @TableField("set_meal_sales_time")
    private Date setMealSalesTime;


    public Integer getSetMealSaleId() {
        return setMealSaleId;
    }

    public void setSetMealSaleId(Integer setMealSaleId) {
        this.setMealSaleId = setMealSaleId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSetMealId() {
        return setMealId;
    }

    public void setSetMealId(Integer setMealId) {
        this.setMealId = setMealId;
    }

    public Date getSetMealSalesTime() {
        return setMealSalesTime;
    }

    public void setSetMealSalesTime(Date setMealSalesTime) {
        this.setMealSalesTime = setMealSalesTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.setMealSaleId;
    }

    @Override
    public String toString() {
        return "SetMealSales{" +
        "setMealSaleId=" + setMealSaleId +
        ", customerId=" + customerId +
        ", setMealId=" + setMealId +
        ", setMealSalesTime=" + setMealSalesTime +
        "}";
    }
}
