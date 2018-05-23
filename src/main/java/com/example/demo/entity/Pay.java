package com.example.demo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wicky
 * @since 2018-05-16
 */
public class Pay extends Model<Pay> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sales_id")
    private Integer salesId;
    @TableField("pay_price")
    private Double payPrice;
    @TableField("payment_time")
    private Date paymentTime;


    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.salesId;
    }

    @Override
    public String toString() {
        return "Pay{" +
        "salesId=" + salesId +
        ", payPrice=" + payPrice +
        ", paymentTime=" + paymentTime +
        "}";
    }
}
