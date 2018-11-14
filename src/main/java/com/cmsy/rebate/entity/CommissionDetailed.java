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
@TableName("t_commission_detailed")
public class CommissionDetailed extends Model<CommissionDetailed> {

    private static final long serialVersionUID = 1L;

    private String id;
    private Integer version;
    @TableField("create_date")
    private Date createDate;
    @TableField("user_id")
    private Integer userId;
    private BigDecimal commission;
    @TableField("flow_type")
    private Integer flowType;
    private String description;
    @TableField("rel_commission")
    private BigDecimal relCommission;
    @TableField("from_user")
    private Integer fromUser;


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

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRelCommission() {
        return relCommission;
    }

    public void setRelCommission(BigDecimal relCommission) {
        this.relCommission = relCommission;
    }

    public Integer getFromUser() {
        return fromUser;
    }

    public void setFromUser(Integer fromUser) {
        this.fromUser = fromUser;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CommissionDetailed{" +
        "id=" + id +
        ", version=" + version +
        ", createDate=" + createDate +
        ", userId=" + userId +
        ", commission=" + commission +
        ", flowType=" + flowType +
        ", description=" + description +
        ", relCommission=" + relCommission +
        ", fromUser=" + fromUser +
        "}";
    }
}
