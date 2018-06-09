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
 * @since 2018-05-03
 */
public class Customer extends Model<Customer> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "customer_id", type = IdType.AUTO)
    private Integer customerId;
    private String name;
    private String phone;
    private Date birth;
    @TableField("customer_role_id")
    private Integer customerRoleId;
    @TableField("history_total_price")
    private Double historyTotalPrice;
    @TableField(exist = false)
    private CustomerRole customerRole;

    public CustomerRole getCustomerRole() {
        return customerRole;
    }

    public void setCustomerRole(CustomerRole customerRole) {
        this.customerRole = customerRole;
    }




    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getCustomerRoleId() {
        return customerRoleId;
    }

    public void setCustomerRoleId(Integer customerRoleId) {
        this.customerRoleId = customerRoleId;
    }

    public Double getHistoryTotalPrice() {
        return historyTotalPrice;
    }

    public void setHistoryTotalPrice(Double historyTotalPrice) {
        this.historyTotalPrice = historyTotalPrice;
    }

    @Override
    protected Serializable pkVal() {
        return this.customerId;
    }

    @Override
    public String toString() {
        return "Customer{" +
        "customerId=" + customerId +
        ", name=" + name +
        ", phone=" + phone +
        ", birth=" + birth +
        ", customerRoleId=" + customerRoleId +
        ", historyTotalPrice=" + historyTotalPrice +
        "}";
    }
}
