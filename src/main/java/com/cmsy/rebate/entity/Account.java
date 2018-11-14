package com.cmsy.rebate.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
import java.sql.Blob;

/**
 * <p>
 * 
 * </p>
 *
 * @author kaka_fun
 * @since 2018-11-13
 */
public class Account extends Model<Account> {

    private static final long serialVersionUID = 1L;

    /**
     * 账号ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("account_name")
    private String accountName;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private Blob nickName;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 头像url
     */
    @TableField("head_url")
    private String headUrl;
    /**
     * 密码md5
     */
    private String pswd;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Integer createTime;
    /**
     * 上次登录的时间
     */
    @TableField("last_time")
    private Integer lastTime;
    /**
     * 上次登录的地址
     */
    @TableField("last_address")
    private String lastAddress;
    /**
     * 账号状态 0正常
     */
    @TableField("account_state")
    private Integer accountState;
    @TableField("login_state")
    private Integer loginState;
    /**
     * 房卡
     */
    private Integer card;
    private Integer gold;
    private Integer integral;
    /**
     * 被赞的次数
     */
    private Integer praise;
    /**
     * 被踩的次数
     */
    private Integer tread;
    /**
     * 牌局记录
     */
    private Blob records;
    @TableField("debug_authority")
    private Integer debugAuthority;
    /**
     * 账号权限
     */
    private Integer authority;
    /**
     * 来源平台
     */
    private Integer pt;
    @TableField("total_count")
    private Integer totalCount;
    private Long phone;
    private String introduce;
    private Integer vipLv;
    @TableField("save_time")
    private Long saveTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Blob getNickName() {
        return nickName;
    }

    public void setNickName(Blob nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getLastTime() {
        return lastTime;
    }

    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastAddress() {
        return lastAddress;
    }

    public void setLastAddress(String lastAddress) {
        this.lastAddress = lastAddress;
    }

    public Integer getAccountState() {
        return accountState;
    }

    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
    }

    public Integer getLoginState() {
        return loginState;
    }

    public void setLoginState(Integer loginState) {
        this.loginState = loginState;
    }

    public Integer getCard() {
        return card;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Integer getTread() {
        return tread;
    }

    public void setTread(Integer tread) {
        this.tread = tread;
    }

    public Blob getRecords() {
        return records;
    }

    public void setRecords(Blob records) {
        this.records = records;
    }

    public Integer getDebugAuthority() {
        return debugAuthority;
    }

    public void setDebugAuthority(Integer debugAuthority) {
        this.debugAuthority = debugAuthority;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public Integer getPt() {
        return pt;
    }

    public void setPt(Integer pt) {
        this.pt = pt;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getVipLv() {
        return vipLv;
    }

    public void setVipLv(Integer vipLv) {
        this.vipLv = vipLv;
    }

    public Long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Long saveTime) {
        this.saveTime = saveTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Account{" +
        "id=" + id +
        ", accountName=" + accountName +
        ", nickName=" + nickName +
        ", sex=" + sex +
        ", headUrl=" + headUrl +
        ", pswd=" + pswd +
        ", createTime=" + createTime +
        ", lastTime=" + lastTime +
        ", lastAddress=" + lastAddress +
        ", accountState=" + accountState +
        ", loginState=" + loginState +
        ", card=" + card +
        ", gold=" + gold +
        ", integral=" + integral +
        ", praise=" + praise +
        ", tread=" + tread +
        ", records=" + records +
        ", debugAuthority=" + debugAuthority +
        ", authority=" + authority +
        ", pt=" + pt +
        ", totalCount=" + totalCount +
        ", phone=" + phone +
        ", introduce=" + introduce +
        ", vipLv=" + vipLv +
        ", saveTime=" + saveTime +
        "}";
    }
}
