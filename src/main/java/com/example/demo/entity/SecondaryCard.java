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
 * @since 2018-05-15
 */
@TableName("secondary_card")
public class SecondaryCard extends Model<SecondaryCard> {

    private static final long serialVersionUID = 1L;

    @TableId("pro_id")
    private Integer proId;
    @TableField("secondary_card_id")
    private Integer secondaryCardId;
    private Integer count;
    @TableField(exist = false)
    private SevProject sevProject;

    public SevProject getSevProject() {
        return sevProject;
    }

    public void setSevProject(SevProject sevProject) {
        this.sevProject = sevProject;
    }


    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getSecondaryCardId() {
        return secondaryCardId;
    }

    public void setSecondaryCardId(Integer secondaryCardId) {
        this.secondaryCardId = secondaryCardId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    protected Serializable pkVal() {
        return this.proId;
    }

    @Override
    public String toString() {
        return "SecondaryCard{" +
        "proId=" + proId +
        ", secondaryCardId=" + secondaryCardId +
        ", count=" + count +
        "}";
    }
}
