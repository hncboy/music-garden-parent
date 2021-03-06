package com.hncboy.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "users_report")
public class UsersReport {

    @Id
    private String id;

    /**
     * 被举报用户id
     */
    @Column(name = "deal_user_id")
    private String dealUserId;

    /**
     * 被举报视频id
     */
    @Column(name = "deal_video_id")
    private String dealVideoId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 举报人id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 举报时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取被举报用户id
     *
     * @return deal_user_id - 被举报用户id
     */
    public String getDealUserId() {
        return dealUserId;
    }

    /**
     * 设置被举报用户id
     *
     * @param dealUserId 被举报用户id
     */
    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }

    /**
     * 获取被举报视频id
     *
     * @return deal_video_id - 被举报视频id
     */
    public String getDealVideoId() {
        return dealVideoId;
    }

    /**
     * 设置被举报视频id
     *
     * @param dealVideoId 被举报视频id
     */
    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取举报人id
     *
     * @return user_id - 举报人id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置举报人id
     *
     * @param userId 举报人id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取举报时间
     *
     * @return create_time - 举报时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置举报时间
     *
     * @param createTime 举报时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}