package com.example.demo.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
public class Waiter extends Model<Waiter> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "waiter_id", type = IdType.AUTO)
    private Integer waiterId;
    @TableField("waiter_name")
    private String waiterName;
    private String phone;



    public Integer getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Integer waiterId) {
        this.waiterId = waiterId;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    protected Serializable pkVal() {
        return this.waiterId;
    }

    @Override
    public String toString() {
        return "Waiter{" +
        "waiterId=" + waiterId +
        ", waiterName=" + waiterName +
        ", phone=" + phone +
        "}";
    }
}
