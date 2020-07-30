package com.funtl.hello.spring.boot.entity;

import com.funtl.hello.spring.boot.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "monitor_config")
public class MonitorConfig extends BaseEntity {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 监控主题名
     */
    private String name;

    /**
     * 是否有效
     */
    private Boolean status;

    /**
     * 是否开启监控
     */
    private Boolean state;

    /**
     * 推送条数
     */
    @Column(name = "push_num")
    private Integer pushNum;

    /**
     * 包含关键词（如果所有关键字则用 .*表示）
     */
    @Column(name = "keyword_search")
    private String keywordSearch;

    /**
     * 同时包含关键字
     */
    @Column(name = "keyword_must")
    private String keywordMust;

    /**
     * 排除关键字
     */
    @Column(name = "keyword_exclude")
    private String keywordExclude;

    /**
     * 站点信源
     */
    private String sites;

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
    public MonitorConfig setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public MonitorConfig setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 获取监控主题名
     *
     * @return name - 监控主题名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置监控主题名
     *
     * @param name 监控主题名
     */
    public MonitorConfig setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取是否有效
     *
     * @return status - 是否有效
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置是否有效
     *
     * @param status 是否有效
     */
    public MonitorConfig setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    /**
     * 获取是否开启监控
     *
     * @return state - 是否开启监控
     */
    public Boolean getState() {
        return state;
    }

    /**
     * 设置是否开启监控
     *
     * @param state 是否开启监控
     */
    public MonitorConfig setState(Boolean state) {
        this.state = state;
        return this;
    }

    /**
     * 获取推送条数
     *
     * @return push_num - 推送条数
     */
    public Integer getPushNum() {
        return pushNum;
    }

    /**
     * 设置推送条数
     *
     * @param pushNum 推送条数
     */
    public MonitorConfig setPushNum(Integer pushNum) {
        this.pushNum = pushNum;
        return this;
    }

    /**
     * 获取包含关键词（如果所有关键字则用 .*表示）
     *
     * @return keyword_search - 包含关键词（如果所有关键字则用 .*表示）
     */
    public String getKeywordSearch() {
        return keywordSearch;
    }

    /**
     * 设置包含关键词（如果所有关键字则用 .*表示）
     *
     * @param keywordSearch 包含关键词（如果所有关键字则用 .*表示）
     */
    public MonitorConfig setKeywordSearch(String keywordSearch) {
        this.keywordSearch = keywordSearch;
        return this;
    }

    /**
     * 获取同时包含关键字
     *
     * @return keyword_must - 同时包含关键字
     */
    public String getKeywordMust() {
        return keywordMust;
    }

    /**
     * 设置同时包含关键字
     *
     * @param keywordMust 同时包含关键字
     */
    public MonitorConfig setKeywordMust(String keywordMust) {
        this.keywordMust = keywordMust;
        return this;
    }

    /**
     * 获取排除关键字
     *
     * @return keyword_exclude - 排除关键字
     */
    public String getKeywordExclude() {
        return keywordExclude;
    }

    /**
     * 设置排除关键字
     *
     * @param keywordExclude 排除关键字
     */
    public MonitorConfig setKeywordExclude(String keywordExclude) {
        this.keywordExclude = keywordExclude;
        return this;
    }

    /**
     * 获取站点信源
     *
     * @return sites - 站点信源
     */
    public String getSites() {
        return sites;
    }

    /**
     * 设置站点信源
     *
     * @param sites 站点信源
     */
    public MonitorConfig setSites(String sites) {
        this.sites = sites;
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
    public MonitorConfig setCreateTime(Date createTime) {
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
    public MonitorConfig setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}