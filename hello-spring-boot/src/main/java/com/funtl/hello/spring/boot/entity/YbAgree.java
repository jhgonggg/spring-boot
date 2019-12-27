package com.funtl.hello.spring.boot.entity;

import com.funtl.hello.spring.boot.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "yb_agree")
public class YbAgree extends BaseEntity {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户 id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 插入时间
     */
    @Column(name = "creat_time")
    private Date creatTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户 id
     *
     * @return user_id - 用户 id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户 id
     *
     * @param userId 用户 id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取插入时间
     *
     * @return creat_time - 插入时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置插入时间
     *
     * @param creatTime 插入时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}