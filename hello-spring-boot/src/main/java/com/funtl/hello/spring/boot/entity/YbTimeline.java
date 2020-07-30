package com.funtl.hello.spring.boot.entity;

import com.funtl.hello.spring.boot.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "yb_timeline")
public class YbTimeline extends BaseEntity {
    /**
     * 时间轴id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 朋友圈消息表id
     */
    private Long fcmid;

    /**
     * 是否是自己的消息 1/是  0/不是
     */
    @Column(name = "is_own")
    private Boolean isOwn;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 获取时间轴id
     *
     * @return id - 时间轴id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置时间轴id
     *
     * @param id 时间轴id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取朋友圈消息表id
     *
     * @return fcmid - 朋友圈消息表id
     */
    public Long getFcmid() {
        return fcmid;
    }

    /**
     * 设置朋友圈消息表id
     *
     * @param fcmid 朋友圈消息表id
     */
    public void setFcmid(Long fcmid) {
        this.fcmid = fcmid;
    }

    /**
     * 获取是否是自己的消息 1/是  0/不是
     *
     * @return is_own - 是否是自己的消息 1/是  0/不是
     */
    public Boolean getIsOwn() {
        return isOwn;
    }

    /**
     * 设置是否是自己的消息 1/是  0/不是
     *
     * @param isOwn 是否是自己的消息 1/是  0/不是
     */
    public void setIsOwn(Boolean isOwn) {
        this.isOwn = isOwn;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}