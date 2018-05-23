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
 * @since 2018-05-16
 */
@TableName("sev_sales")
public class SevSales extends Model<SevSales> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sales_id", type = IdType.AUTO)
    private Integer salesId;
    @TableField("sales_time")
    private Date salesTime;
    @TableField("total_price")
    private Double totalPrice;
    @TableField("customer_id")
    private Integer customerId;
    private String state;
    //private Pay pay;


    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public Date getSalesTime() {
        return salesTime;
    }

    public void setSalesTime(Date salesTime) {
        this.salesTime = salesTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
//
//    public Pay getPay() {
//        return pay;
//    }
//
//    public void setPay(Pay pay) {
//        this.pay = pay;
//    }
    @Override
    protected Serializable pkVal() {
        return this.salesId;
    }

    @Override
    public String toString() {
        return "SevSales{" +
        "salesId=" + salesId +
        ", salesTime=" + salesTime +
        ", totalPrice=" + totalPrice +
        ", customerId=" + customerId +
        ", state=" + state +
        "}";
    }
}
