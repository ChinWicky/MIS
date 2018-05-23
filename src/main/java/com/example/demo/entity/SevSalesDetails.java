package com.example.demo.entity;

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
 * @since 2018-05-04
 */
@TableName("sev_sales_details")
public class SevSalesDetails extends Model<SevSalesDetails> {

    private static final long serialVersionUID = 1L;

    @TableId("sales_id")
    private Integer salesId;
    @TableField("pro_id")
    private Integer proId;
    @TableField("waiter_id")
    private Integer waiterId;
    @TableField("post_discount_price")
    private Double postDiscountPrice;
    @TableField("payment_method")
    private String paymentMethod;
    private Integer quantity;
    @TableField(exist = false)
    private Waiter waiter;

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Integer waiterId) {
        this.waiterId = waiterId;
    }

    public Double getPostDiscountPrice() {
        return postDiscountPrice;
    }

    public void setPostDiscountPrice(Double postDiscountPrice) {
        this.postDiscountPrice = postDiscountPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    @Override
    protected Serializable pkVal() {
        return this.salesId;
    }

    @Override
    public String toString() {
        return "SevSalesDetails{" +
        "salesId=" + salesId +
        ", proId=" + proId +
        ", waiterId=" + waiterId +
        ", postDiscountPrice=" + postDiscountPrice +
        ", paymentMethod=" + paymentMethod +
        ", quantity=" + quantity +
        "}";
    }
}
