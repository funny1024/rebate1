package com.cmsy.rebate.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author kaka_fun
 * @since 2018-11-13
 */
@TableName("t_user_consumption")
public class UserConsumption extends Model<UserConsumption> {

    private static final long serialVersionUID = 1L;

    private String id;
    private Integer version;
    @TableField("create_date")
    private Date createDate;
    @TableField("user_id")
    private Integer userId;
    private BigDecimal consumption;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserConsumption{" +
        "id=" + id +
        ", version=" + version +
        ", createDate=" + createDate +
        ", userId=" + userId +
        ", consumption=" + consumption +
        "}";
    }
}
