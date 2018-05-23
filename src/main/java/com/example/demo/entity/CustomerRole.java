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
@TableName("customer_role")
public class CustomerRole extends Model<CustomerRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "customer_role_id", type = IdType.AUTO)
    private Integer customerRoleId;
    @TableField("customer_role_name")
    private String customerRoleName;


    public Integer getCustomerRoleId() {
        return customerRoleId;
    }

    public void setCustomerRoleId(Integer customerRoleId) {
        this.customerRoleId = customerRoleId;
    }

    public String getCustomerRoleName() {
        return customerRoleName;
    }

    public void setCustomerRoleName(String customerRoleName) {
        this.customerRoleName = customerRoleName;
    }

    @Override
    protected Serializable pkVal() {
        return this.customerRoleId;
    }

    @Override
    public String toString() {
        return "CustomerRole{" +
        "customerRoleId=" + customerRoleId +
        ", customerRoleName=" + customerRoleName +
        "}";
    }
}
