package com.hncboy.service;

import com.hncboy.pojo.Videos;
import com.hncboy.utils.PagedResult;

import java.util.List;

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

    /**
     * 分页查询视频列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    PagedResult getAllVideos(Videos video, Integer isSaveRecord, Integer page, Integer pageSize);

    /**
     * 获取热搜词列表
     *
     * @return
     */
    List<String> getHotWords();

    /**
     * 用户喜欢视频/点赞视频
     *
     * @param userId
     * @param videoId
     * @param videoCreatorId
     */
    void userLikeVideo(String userId, String videoId, String videoCreatorId);

    /**
     * 用户不喜欢视频/取消点赞
     *
     * @param userId
     * @param videoId
     * @param videoCreatorId
     */
    void userUnLikeVideo(String userId, String videoId, String videoCreatorId);

    /**
     * 查询我喜欢的视频列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedResult queryMyLikeVideos(String userId, Integer page, Integer pageSize);

    /**
     * 查询我关注的人的视频列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedResult queryMyFollowVideos(String userId, Integer page, Integer pageSize);
}
