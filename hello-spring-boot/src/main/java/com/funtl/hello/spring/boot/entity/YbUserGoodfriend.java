package com.funtl.hello.spring.boot.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yb_user_goodfriend")
public class YbUserGoodfriend {
    /**
     * 好友表ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date updated;

    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 好友ID
     */
    @Column(name = "friend_id")
    private Long friendId;

    private Date created;

    /**
     * 获取好友表ID
     *
     * @return id - 好友表ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置好友表ID
     *
     * @param id 好友表ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * 获取用户ID
     *
     * @return uid - 用户ID
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户ID
     *
     * @param uid 用户ID
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取好友ID
     *
     * @return friend_id - 好友ID
     */
    public Long getFriendId() {
        return friendId;
    }

    /**
     * 设置好友ID
     *
     * @param friendId 好友ID
     */
    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}