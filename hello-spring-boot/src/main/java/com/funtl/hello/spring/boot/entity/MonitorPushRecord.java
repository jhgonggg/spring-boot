package com.funtl.hello.spring.boot.entity;

import com.funtl.hello.spring.boot.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "monitor_push_record")
public class MonitorPushRecord extends BaseEntity {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 配置id
     */
    @Column(name = "config_id")
    private Integer configId;

    /**
     * 对应推送表主键id
     */
    @Column(name = "push_id")
    private Integer pushId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 推送消息
     */
    private String msg;

    /**
     * 是否已经推送
     */
    @Column(name = "is_push")
    private Boolean isPush;

    /**
     * 创建时间
     */
    @Column(name = "push_time")
    private Date pushTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public MonitorPushRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 获取配置id
     *
     * @return config_id - 配置id
     */
    public Integer getConfigId() {
        return configId;
    }

    /**
     * 设置配置id
     *
     * @param configId 配置id
     */
    public MonitorPushRecord setConfigId(Integer configId) {
        this.configId = configId;
        return this;
    }

    /**
     * 获取对应推送表主键id
     *
     * @return push_id - 对应推送表主键id
     */
    public Integer getPushId() {
        return pushId;
    }

    /**
     * 设置对应推送表主键id
     *
     * @param pushId 对应推送表主键id
     */
    public MonitorPushRecord setPushId(Integer pushId) {
        this.pushId = pushId;
        return this;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public MonitorPushRecord setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 获取推送消息
     *
     * @return msg - 推送消息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置推送消息
     *
     * @param msg 推送消息
     */
    public MonitorPushRecord setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 获取是否已经推送
     *
     * @return is_push - 是否已经推送
     */
    public Boolean getIsPush() {
        return isPush;
    }

    /**
     * 设置是否已经推送
     *
     * @param isPush 是否已经推送
     */
    public MonitorPushRecord setIsPush(Boolean isPush) {
        this.isPush = isPush;
        return this;
    }

    /**
     * 获取创建时间
     *
     * @return push_time - 创建时间
     */
    public Date getPushTime() {
        return pushTime;
    }

    /**
     * 设置创建时间
     *
     * @param pushTime 创建时间
     */
    public MonitorPushRecord setPushTime(Date pushTime) {
        this.pushTime = pushTime;
        return this;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public MonitorPushRecord setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}