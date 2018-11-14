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
@TableName("t_user_rebate")
public class TUserRebate extends Model<TUserRebate> {

    private static final long serialVersionUID = 1L;

    private String id;
    private Integer version;
    @TableField("create_date")
    private Date createDate;
    @TableField("user_id")
    private Integer userId;
    @TableField("parent_id")
    private Integer parentId;
    private Integer b;
    private Integer a;
    private Integer level;
    private Integer area;
    private BigDecimal commission;
    @TableField("total_commission")
    private BigDecimal totalCommission;
    @TableField("today_commission")
    private BigDecimal todayCommission;
    @TableField("last_day_commission")
    private BigDecimal lastDayCommission;
    @TableField("total_consumption")
    private BigDecimal totalConsumption;
    @TableField("today_consumption")
    private BigDecimal todayConsumption;
    @TableField("last_day_consumption")
    private BigDecimal lastDayConsumption;
    @TableField("bind_date")
    private Date bindDate;
    @TableField("group_name")
    private String groupName;
    @TableField("rebate_ratio")
    private BigDecimal rebateRatio;
    @TableField("daqu_commission")
    private BigDecimal daquCommission;
    @TableField("daqu_total_commission")
    private BigDecimal daquTotalCommission;


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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(BigDecimal totalCommission) {
        this.totalCommission = totalCommission;
    }

    public BigDecimal getTodayCommission() {
        return todayCommission;
    }

    public void setTodayCommission(BigDecimal todayCommission) {
        this.todayCommission = todayCommission;
    }

    public BigDecimal getLastDayCommission() {
        return lastDayCommission;
    }

    public void setLastDayCommission(BigDecimal lastDayCommission) {
        this.lastDayCommission = lastDayCommission;
    }

    public BigDecimal getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(BigDecimal totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public BigDecimal getTodayConsumption() {
        return todayConsumption;
    }

    public void setTodayConsumption(BigDecimal todayConsumption) {
        this.todayConsumption = todayConsumption;
    }

    public BigDecimal getLastDayConsumption() {
        return lastDayConsumption;
    }

    public void setLastDayConsumption(BigDecimal lastDayConsumption) {
        this.lastDayConsumption = lastDayConsumption;
    }

    public Date getBindDate() {
        return bindDate;
    }

    public void setBindDate(Date bindDate) {
        this.bindDate = bindDate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public BigDecimal getRebateRatio() {
        return rebateRatio;
    }

    public void setRebateRatio(BigDecimal rebateRatio) {
        this.rebateRatio = rebateRatio;
    }

    public BigDecimal getDaquCommission() {
        return daquCommission;
    }

    public void setDaquCommission(BigDecimal daquCommission) {
        this.daquCommission = daquCommission;
    }

    public BigDecimal getDaquTotalCommission() {
        return daquTotalCommission;
    }

    public void setDaquTotalCommission(BigDecimal daquTotalCommission) {
        this.daquTotalCommission = daquTotalCommission;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TUserRebate{" +
        "id=" + id +
        ", version=" + version +
        ", createDate=" + createDate +
        ", userId=" + userId +
        ", parentId=" + parentId +
        ", b=" + b +
        ", a=" + a +
        ", level=" + level +
        ", area=" + area +
        ", commission=" + commission +
        ", totalCommission=" + totalCommission +
        ", todayCommission=" + todayCommission +
        ", lastDayCommission=" + lastDayCommission +
        ", totalConsumption=" + totalConsumption +
        ", todayConsumption=" + todayConsumption +
        ", lastDayConsumption=" + lastDayConsumption +
        ", bindDate=" + bindDate +
        ", groupName=" + groupName +
        ", rebateRatio=" + rebateRatio +
        ", daquCommission=" + daquCommission +
        ", daquTotalCommission=" + daquTotalCommission +
        "}";
    }
}
