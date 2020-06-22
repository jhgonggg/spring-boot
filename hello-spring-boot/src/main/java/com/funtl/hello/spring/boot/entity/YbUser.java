package com.funtl.hello.spring.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Table(name = "yb_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YbUser {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 出生日期
     */
    @NotNull
    private Date birth;

    /**
     * 头像地址
     */
    private String picture;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 地理位置信息
     */
    private String location;

    /**
     * 手机号
     */
    private String phone;

    private Date updated;

    /**
     * 在线状态 1：在线  0：离线
     */
    @Column(name = "is_online")
    private String isOnline;

    @Column(name = "is_role")
    private Integer isRole;
    @Transient
    private String profession;

    /**
     * 获取用户ID
     *
     * @return id - 用户ID
     */
    public Long getId() {
        return id;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取出生日期
     *
     * @return birth - 出生日期
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * 设置出生日期
     *
     * @param birth 出生日期
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * 获取头像地址
     *
     * @return picture - 头像地址
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置头像地址
     *
     * @param picture 头像地址
     */
    public void setPicture(String picture) {
        this.picture = picture;
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

    /**
     * 获取地理位置信息
     *
     * @return location - 地理位置信息
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置地理位置信息
     *
     * @param location 地理位置信息
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * 获取在线状态 1：在线  0：离线
     *
     * @return is_online - 在线状态 1：在线  0：离线
     */
    public String getIsOnline() {
        return isOnline;
    }

    /**
     * 设置在线状态 1：在线  0：离线
     *
     * @param isOnline 在线状态 1：在线  0：离线
     */
    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    /**
     * @return is_role
     */
    public Integer getIsRole() {
        return isRole;
    }

    /**
     * @param isRole
     */
    public void setIsRole(Integer isRole) {
        this.isRole = isRole;
    }

    @Override
    public String toString() {
        return "YbUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", birth=" + birth +
                ", picture='" + picture + '\'' +
                ", created=" + created +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", updated=" + updated +
                ", isOnline='" + isOnline + '\'' +
                ", isRole=" + isRole +
                '}';
    }
}