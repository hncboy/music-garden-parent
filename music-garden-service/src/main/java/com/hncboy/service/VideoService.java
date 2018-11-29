package com.hncboy.service;

import com.hncboy.pojo.Videos;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/29
 * Time: 14:15
 */
public interface VideoService {

    /**
     * 保存视频
     *
     * @param video
     */
    String saveVideo(Videos video);

    /**
     * 修改视频封面
     *
     * @param videoId
     * @param coverPath
     * @return
     */
    void updateVideo(String videoId, String coverPath);
}
