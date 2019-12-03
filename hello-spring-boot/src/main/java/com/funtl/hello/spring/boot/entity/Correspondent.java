package com.funtl.hello.spring.boot.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Correspondent {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 单位
     */
    private String unit;

    /**
     * 类型，1-新闻秘书  2-通讯员
     */
    private Integer type;

    /**
     * 地区，21个地市+其他，共22个选项
     */
    private String area;

    /**
     * 部门
     */
    private String dept;

    /**
     * 简介
     */
    private String description;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 添加人
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 添加时间
     */
    @Column(name = "status")
    private Integer status;

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
    public Correspondent setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public Correspondent setName(String name) {
        this.name = name;
        return this;
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
    public Correspondent setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * 获取单位
     *
     * @return unit - 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public Correspondent setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    /**
     * 获取类型，1-新闻秘书  2-通讯员
     *
     * @return type - 类型，1-新闻秘书  2-通讯员
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型，1-新闻秘书  2-通讯员
     *
     * @param type 类型，1-新闻秘书  2-通讯员
     */
    public Correspondent setType(Integer type) {
        this.type = type;
        return this;
    }

    /**
     * 获取地区，21个地市+其他，共22个选项
     *
     * @return area - 地区，21个地市+其他，共22个选项
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置地区，21个地市+其他，共22个选项
     *
     * @param area 地区，21个地市+其他，共22个选项
     */
    public Correspondent setArea(String area) {
        this.area = area;
        return this;
    }

    /**
     * 获取部门
     *
     * @return dept - 部门
     */
    public String getDept() {
        return dept;
    }

    /**
     * 设置部门
     *
     * @param dept 部门
     */
    public Correspondent setDept(String dept) {
        this.dept = dept;
        return this;
    }

    /**
     * 获取简介
     *
     * @return desc - 简介
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置简介
     *
     * @param description 简介
     */
    public Correspondent setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public Correspondent setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    /**
     * 获取添加人
     *
     * @return user_id - 添加人
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置添加人
     *
     * @param userId 添加人
     */
    public Correspondent setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 获取添加时间
     *
     * @return create_time - 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
     */
    public Correspondent setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Correspondent setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "Correspondent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", unit='" + unit + '\'' +
                ", type=" + type +
                ", area='" + area + '\'' +
                ", dept='" + dept + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}