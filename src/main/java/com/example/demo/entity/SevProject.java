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
 * @since 2018-03-26
 */
@TableName("sev_project")
public class SevProject extends Model<SevProject> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pro_id", type = IdType.AUTO)
    private Integer proId;
    @TableField("pro_name")
    private String proName;
    private Double price;
    @TableField("ser_time")
    private Double serTime;
    @TableField("hig_price")
    private Double higPrice;
    @TableField("pri_price")
    private Double priPrice;
    private Integer typeid;
    @TableField(exist = false)
    private Type type;


    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSerTime() {
        return serTime;
    }

    public void setSerTime(Double serTime) {
        this.serTime = serTime;
    }

    public Double getHigPrice() {
        return higPrice;
    }

    public void setHigPrice(Double higPrice) {
        this.higPrice = higPrice;
    }

    public Double getPriPrice() {
        return priPrice;
    }

    public void setPriPrice(Double priPrice) {
        this.priPrice = priPrice;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Type getType() { return type; }
    
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.proId;
    }

    @Override
    public String toString() {
        return "SevProject{" +
        "proId=" + proId +
        ", proName=" + proName +
        ", price=" + price +
        ", serTime=" + serTime +
        ", higPrice=" + higPrice +
        ", priPrice=" + priPrice +
        ", typeid=" + typeid+
        "}";
    }
}
