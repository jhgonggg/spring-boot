package com.funtl.hello.spring.boot.entity;

import com.funtl.hello.spring.boot.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "monitor_site")
public class MonitorSite extends BaseEntity {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 所属组编码
     */
    @Column(name = "group_id")
    private String groupId;

    /**
     * 所属组（类别）
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 站点类型
     */
    private String type;

    /**
     * 是否有效
     */
    private Integer status;

    /**
     * 编码
     */
    private String code;

    /**
     * 站点
     */
    private String name;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
    public MonitorSite setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 获取所属组编码
     *
     * @return group_id - 所属组编码
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置所属组编码
     *
     * @param groupId 所属组编码
     */
    public MonitorSite setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 获取所属组（类别）
     *
     * @return group_name - 所属组（类别）
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置所属组（类别）
     *
     * @param groupName 所属组（类别）
     */
    public MonitorSite setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 获取站点类型
     *
     * @return type - 站点类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置站点类型
     *
     * @param type 站点类型
     */
    public MonitorSite setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 获取是否有效
     *
     * @return status - 是否有效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否有效
     *
     * @param status 是否有效
     */
    public MonitorSite setStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * 获取编码
     *
     * @return code - 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编码
     *
     * @param code 编码
     */
    public MonitorSite setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * 获取站点
     *
     * @return name - 站点
     */
    public String getName() {
        return name;
    }

    /**
     * 设置站点
     *
     * @param name 站点
     */
    public MonitorSite setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public MonitorSite setState(Integer state) {
        this.state = state;
        return this;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public MonitorSite setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    public MonitorSite setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}