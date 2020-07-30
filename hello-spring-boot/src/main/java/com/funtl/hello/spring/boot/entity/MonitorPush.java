package com.funtl.hello.spring.boot.entity;

import com.funtl.hello.spring.boot.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "monitor_push")
public class MonitorPush extends BaseEntity {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 来自系统
     */
    private String system;

    /**
     * 系统唯一id
     */
    @Column(name = "doc_id")
    private String docId;

    /**
     * 抓取标题
     */
    private String title;

    /**
     * 抓取ip
     */
    @Column(name = "send_ip")
    private String sendIp;

    /**
     * 抓取时间
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * 对应信源名称
     */
    @Column(name = "media_name")
    private String mediaName;

    /**
     * 链接
     */
    private String url;

    /**
     * 来自平台
     */
    private String platform;

    /**
     * 接收推送时间
     */
    @Column(name = "push_time")
    private Date pushTime;

    /**
     * 摘要
     */
    private String summary;

    /**
     * pv
     */
    @Column(name = "pv_count")
    private Integer pvCount;

    /**
     * 对应信源编码
     */
    @Column(name = "media_id")
    private String mediaId;

    /**
     * 回复数
     */
    @Column(name = "reply_count")
    private Integer replyCount;

    /**
     * 入库时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 抓取内容
     */
    private String content;

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
    public MonitorPush setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 获取来自系统
     *
     * @return system - 来自系统
     */
    public String getSystem() {
        return system;
    }

    /**
     * 设置来自系统
     *
     * @param system 来自系统
     */
    public MonitorPush setSystem(String system) {
        this.system = system;
        return this;
    }

    /**
     * 获取系统唯一id
     *
     * @return doc_id - 系统唯一id
     */
    public String getDocId() {
        return docId;
    }

    /**
     * 设置系统唯一id
     *
     * @param docId 系统唯一id
     */
    public MonitorPush setDocId(String docId) {
        this.docId = docId;
        return this;
    }

    /**
     * 获取抓取标题
     *
     * @return title - 抓取标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置抓取标题
     *
     * @param title 抓取标题
     */
    public MonitorPush setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 获取抓取ip
     *
     * @return send_ip - 抓取ip
     */
    public String getSendIp() {
        return sendIp;
    }

    /**
     * 设置抓取ip
     *
     * @param sendIp 抓取ip
     */
    public MonitorPush setSendIp(String sendIp) {
        this.sendIp = sendIp;
        return this;
    }

    /**
     * 获取抓取时间
     *
     * @return send_time - 抓取时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置抓取时间
     *
     * @param sendTime 抓取时间
     */
    public MonitorPush setSendTime(Date sendTime) {
        this.sendTime = sendTime;
        return this;
    }

    /**
     * 获取对应信源名称
     *
     * @return media_name - 对应信源名称
     */
    public String getMediaName() {
        return mediaName;
    }

    /**
     * 设置对应信源名称
     *
     * @param mediaName 对应信源名称
     */
    public MonitorPush setMediaName(String mediaName) {
        this.mediaName = mediaName;
        return this;
    }

    /**
     * 获取链接
     *
     * @return url - 链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置链接
     *
     * @param url 链接
     */
    public MonitorPush setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 获取来自平台
     *
     * @return platform - 来自平台
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 设置来自平台
     *
     * @param platform 来自平台
     */
    public MonitorPush setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    /**
     * 获取接收推送时间
     *
     * @return push_time - 接收推送时间
     */
    public Date getPushTime() {
        return pushTime;
    }

    /**
     * 设置接收推送时间
     *
     * @param pushTime 接收推送时间
     */
    public MonitorPush setPushTime(Date pushTime) {
        this.pushTime = pushTime;
        return this;
    }

    /**
     * 获取摘要
     *
     * @return summary - 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置摘要
     *
     * @param summary 摘要
     */
    public MonitorPush setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * 获取pv
     *
     * @return pv_count - pv
     */
    public Integer getPvCount() {
        return pvCount;
    }

    /**
     * 设置pv
     *
     * @param pvCount pv
     */
    public MonitorPush setPvCount(Integer pvCount) {
        this.pvCount = pvCount;
        return this;
    }

    /**
     * 获取对应信源编码
     *
     * @return media_id - 对应信源编码
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 设置对应信源编码
     *
     * @param mediaId 对应信源编码
     */
    public MonitorPush setMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    /**
     * 获取回复数
     *
     * @return reply_count - 回复数
     */
    public Integer getReplyCount() {
        return replyCount;
    }

    /**
     * 设置回复数
     *
     * @param replyCount 回复数
     */
    public MonitorPush setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
        return this;
    }

    /**
     * 获取入库时间
     *
     * @return create_time - 入库时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置入库时间
     *
     * @param createTime 入库时间
     */
    public MonitorPush setCreateTime(Date createTime) {
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
    public MonitorPush setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    /**
     * 获取抓取内容
     *
     * @return content - 抓取内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置抓取内容
     *
     * @param content 抓取内容
     */
    public MonitorPush setContent(String content) {
        this.content = content;
        return this;
    }
}