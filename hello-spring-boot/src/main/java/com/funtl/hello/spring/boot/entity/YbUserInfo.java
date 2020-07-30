package com.funtl.hello.spring.boot.entity;

import com.funtl.hello.spring.boot.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "yb_user_info")
public class YbUserInfo extends BaseEntity {
    /**
     * 用户详情ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 职业
     */
    private String profession;

    /**
     * 个性签名
     */
    @Column(name = "personal_signature")
    private String personalSignature;

    /**
     * 爱好
     */
    private String hobby;

    private Date updated;

    private Date created;

    /**
     * 获取用户详情ID
     *
     * @return id - 用户详情ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户详情ID
     *
     * @param id 用户详情ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取职业
     *
     * @return profession - 职业
     */
    public String getProfession() {
        return profession;
    }

    /**
     * 设置职业
     *
     * @param profession 职业
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * 获取个性签名
     *
     * @return personal_signature - 个性签名
     */
    public String getPersonalSignature() {
        return personalSignature;
    }

    /**
     * 设置个性签名
     *
     * @param personalSignature 个性签名
     */
    public void setPersonalSignature(String personalSignature) {
        this.personalSignature = personalSignature;
    }

    /**
     * 获取爱好
     *
     * @return hobby - 爱好
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * 设置爱好
     *
     * @param hobby 爱好
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
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