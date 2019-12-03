package com.funtl.hello.spring.boot.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "yb_record")
public class YbRecord {
    /**
     * 聊天记录 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 聊天内容
     */
    private String message;

    /**
     * 发送方
     */
    private Date created;

    @Column(name = "sender_id")
    private Integer senderId;

    @Column(name = "receiver_id")
    private String receiverId;

    /**
     * 消息类型--1 代表已读 0 代表未读
     */
    private Boolean type;

    /**
     * 获取聊天记录 ID
     *
     * @return id - 聊天记录 ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置聊天记录 ID
     *
     * @param id 聊天记录 ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取聊天内容
     *
     * @return message - 聊天内容
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置聊天内容
     *
     * @param message 聊天内容
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取发送方
     *
     * @return created - 发送方
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置发送方
     *
     * @param created 发送方
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return sender_id
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * @param senderId
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * @return receiver_id
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * @param receiverId
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 获取消息类型--1 代表已读 0 代表未读
     *
     * @return type - 消息类型--1 代表已读 0 代表未读
     */
    public Boolean getType() {
        return type;
    }

    /**
     * 设置消息类型--1 代表已读 0 代表未读
     *
     * @param type 消息类型--1 代表已读 0 代表未读
     */
    public void setType(Boolean type) {
        this.type = type;
    }
}