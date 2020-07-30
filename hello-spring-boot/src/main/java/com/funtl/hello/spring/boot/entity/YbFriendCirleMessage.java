package com.funtl.hello.spring.boot.entity;

import com.funtl.hello.spring.boot.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "yb_friend_cirle_message")
public class YbFriendCirleMessage extends BaseEntity {
    /**
     * 消息表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 该用户发表的朋友圈内容
     */
    private String content;

    /**
     * 图片地址
     */
    private String picture;

    /**
     * 发表朋友圈地址
     */
    private String location;

    /**
     * 点赞数 默认0
     */
    @Column(name = "praise_num")
    private Integer praiseNum;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 获取消息表id
     *
     * @return id - 消息表id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置消息表id
     *
     * @param id 消息表id
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
     * 获取该用户发表的朋友圈内容
     *
     * @return content - 该用户发表的朋友圈内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置该用户发表的朋友圈内容
     *
     * @param content 该用户发表的朋友圈内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取图片地址
     *
     * @return picture - 图片地址
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置图片地址
     *
     * @param picture 图片地址
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取发表朋友圈地址
     *
     * @return location - 发表朋友圈地址
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置发表朋友圈地址
     *
     * @param location 发表朋友圈地址
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取点赞数 默认0
     *
     * @return praise_num - 点赞数 默认0
     */
    public Integer getPraiseNum() {
        return praiseNum;
    }

    /**
     * 设置点赞数 默认0
     *
     * @param praiseNum 点赞数 默认0
     */
    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
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