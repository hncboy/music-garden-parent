package com.hncboy.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Videos {

    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 音频id
     */
    @Column(name = "audio_id")
    private String audioId;

    /**
     * 视频描述
     */
    @Column(name = "video_desc")
    private String videoDesc;

    /**
     * 视频路径
     */
    @Column(name = "video_path")
    private String videoPath;

    /**
     * 视频播放秒数
     */
    @Column(name = "video_seconds")
    private Float videoSeconds;

    /**
     * 视频宽度
     */
    @Column(name = "video_width")
    private Integer videoWidth;

    /**
     * 视频高度
     */
    @Column(name = "video_height")
    private Integer videoHeight;

    /**
     * 封面路径
     */
    @Column(name = "cover_path")
    private String coverPath;

    /**
     * 获赞数
     */
    @Column(name = "like_counts")
    private Long likeCounts;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建事件
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
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取音频id
     *
     * @return audio_id - 音频id
     */
    public String getAudioId() {
        return audioId;
    }

    /**
     * 设置音频id
     *
     * @param audioId 音频id
     */
    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }

    /**
     * 获取视频描述
     *
     * @return video_desc - 视频描述
     */
    public String getVideoDesc() {
        return videoDesc;
    }

    /**
     * 设置视频描述
     *
     * @param videoDesc 视频描述
     */
    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    /**
     * 获取视频路径
     *
     * @return video_path - 视频路径
     */
    public String getVideoPath() {
        return videoPath;
    }

    /**
     * 设置视频路径
     *
     * @param videoPath 视频路径
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * 获取视频播放秒数
     *
     * @return video_seconds - 视频播放秒数
     */
    public Float getVideoSeconds() {
        return videoSeconds;
    }

    /**
     * 设置视频播放秒数
     *
     * @param videoSeconds 视频播放秒数
     */
    public void setVideoSeconds(Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    /**
     * 获取视频宽度
     *
     * @return video_width - 视频宽度
     */
    public Integer getVideoWidth() {
        return videoWidth;
    }

    /**
     * 设置视频宽度
     *
     * @param videoWidth 视频宽度
     */
    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    /**
     * 获取视频高度
     *
     * @return video_height - 视频高度
     */
    public Integer getVideoHeight() {
        return videoHeight;
    }

    /**
     * 设置视频高度
     *
     * @param videoHeight 视频高度
     */
    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    /**
     * 获取封面路径
     *
     * @return cover_path - 封面路径
     */
    public String getCoverPath() {
        return coverPath;
    }

    /**
     * 设置封面路径
     *
     * @param coverPath 封面路径
     */
    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    /**
     * 获取获赞数
     *
     * @return like_counts - 获赞数
     */
    public Long getLikeCounts() {
        return likeCounts;
    }

    /**
     * 设置获赞数
     *
     * @param likeCounts 获赞数
     */
    public void setLikeCounts(Long likeCounts) {
        this.likeCounts = likeCounts;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建事件
     *
     * @return create_time - 创建事件
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建事件
     *
     * @param createTime 创建事件
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}