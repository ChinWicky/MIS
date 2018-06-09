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
 * @since 2018-05-03
 */
@TableName("set_meal_sales_detail")
public class SetMealSalesDetail extends Model<SetMealSalesDetail> {

    private static final long serialVersionUID = 1L;

    @TableId("set_meal_id")
    private Integer setMealId;
    @TableField("pro_id")
    private Integer proId;
    @TableField("total_count")
    private Integer totalCount;
    @TableField(exist = false)
    private SetMeal setMeal;
    @TableField(exist = false)
    private SevProject sevProject;


    public Integer getSetMealId() {
        return setMealId;
    }

    public void setSetMealId(Integer setMealId) {
        this.setMealId = setMealId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public SetMeal getSetMeal() {
        return setMeal;
    }

    public void setSetMeal(SetMeal setMeal) {
        this.setMeal = setMeal;
    }

    public SevProject getSevProject() {
        return sevProject;
    }

    public void setSevProject(SevProject sevProject) {
        this.sevProject = sevProject;
    }

    @Override
    protected Serializable pkVal() {
        return this.setMealId;
    }


    @Override
    public String toString() {
        return "SetMealSalesDetail{" +
        "setMealId=" + setMealId +
        ", proId=" + proId +
        ", totalCount=" + totalCount +
        "}";
    }
}
