package com.hncboy.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users_fans")
public class UsersFans {

    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 粉丝id
     */
    @Column(name = "fan_id")
    private String fanId;

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
     * 获取粉丝id
     *
     * @return fan_id - 粉丝id
     */
    public String getFanId() {
        return fanId;
    }

    /**
     * 设置粉丝id
     *
     * @param fanId 粉丝id
     */
    public void setFanId(String fanId) {
        this.fanId = fanId;
    }
}