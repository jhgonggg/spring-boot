package com.funtl.hello.spring.boot.entity;

import com.funtl.hello.spring.boot.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "yb_praise_detail")
public class YbPraiseDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 点赞的用户id
     */
    @Column(name = "praise_uid")
    private Long praiseUid;

    /**
     * 朋友圈的id
     */
    private Long fcmid;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取点赞的用户id
     *
     * @return praise_uid - 点赞的用户id
     */
    public Long getPraiseUid() {
        return praiseUid;
    }

    /**
     * 设置点赞的用户id
     *
     * @param praiseUid 点赞的用户id
     */
    public void setPraiseUid(Long praiseUid) {
        this.praiseUid = praiseUid;
    }

    /**
     * 获取朋友圈的id
     *
     * @return fcmid - 朋友圈的id
     */
    public Long getFcmid() {
        return fcmid;
    }

    /**
     * 设置朋友圈的id
     *
     * @param fcmid 朋友圈的id
     */
    public void setFcmid(Long fcmid) {
        this.fcmid = fcmid;
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