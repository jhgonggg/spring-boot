package com.funtl.hello.spring.boot.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "yb_comment")
public class YbComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 被评论的朋友圈id
     */
    private Long fcmid;

    /**
     * 评论人id
     */
    @Column(name = "commentator_id")
    private Long commentatorId;

    /**
     * 父评论id
     */
    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date created;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取被评论的朋友圈id
     *
     * @return fcmid - 被评论的朋友圈id
     */
    public Long getFcmid() {
        return fcmid;
    }

    /**
     * 设置被评论的朋友圈id
     *
     * @param fcmid 被评论的朋友圈id
     */
    public void setFcmid(Long fcmid) {
        this.fcmid = fcmid;
    }

    /**
     * 获取评论人id
     *
     * @return commentator_id - 评论人id
     */
    public Long getCommentatorId() {
        return commentatorId;
    }

    /**
     * 设置评论人id
     *
     * @param commentatorId 评论人id
     */
    public void setCommentatorId(Long commentatorId) {
        this.commentatorId = commentatorId;
    }

    /**
     * 获取父评论id
     *
     * @return parent_comment_id - 父评论id
     */
    public Long getParentCommentId() {
        return parentCommentId;
    }

    /**
     * 设置父评论id
     *
     * @param parentCommentId 父评论id
     */
    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    /**
     * 获取评论的内容
     *
     * @return content - 评论的内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论的内容
     *
     * @param content 评论的内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取评论时间
     *
     * @return created - 评论时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置评论时间
     *
     * @param created 评论时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}