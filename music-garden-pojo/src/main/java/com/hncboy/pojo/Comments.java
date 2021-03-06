package com.hncboy.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Comments {

    @Id
    private String id;

    /**
     * 回复评论的回复者id
     */
    @Column(name = "to_user_id")
    private String toUserId;

    /**
     * 回复评论的id
     */
    @Column(name = "father_comment_id")
    private String fatherCommentId;

    /**
     * 视频id
     */
    @Column(name = "video_id")
    private String videoId;

    /**
     * 留言者id
     */
    @Column(name = "from_user_id")
    private String fromUserId;

    /**
     * 评论时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 评论内容
     */
    private String comment;

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
     * 获取回复评论的回复者id
     *
     * @return to_user_id - 回复评论的回复者id
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * 设置回复评论的回复者id
     *
     * @param toUserId 回复评论的回复者id
     */
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 获取回复评论的id
     *
     * @return father_comment_id - 回复评论的id
     */
    public String getFatherCommentId() {
        return fatherCommentId;
    }

    /**
     * 设置回复评论的id
     *
     * @param fatherCommentId 回复评论的id
     */
    public void setFatherCommentId(String fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    /**
     * 获取视频id
     *
     * @return video_id - 视频id
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 设置视频id
     *
     * @param videoId 视频id
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取留言者id
     *
     * @return from_user_id - 留言者id
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * 设置留言者id
     *
     * @param fromUserId 留言者id
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * 获取评论时间
     *
     * @return create_time - 评论时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置评论时间
     *
     * @param createTime 评论时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取评论内容
     *
     * @return comment - 评论内容
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置评论内容
     *
     * @param comment 评论内容
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}