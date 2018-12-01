package com.hncboy.mapper;

import com.hncboy.pojo.Videos;
import com.hncboy.pojo.vo.VideosVO;
import com.hncboy.utils.MyMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideosMapperCustom extends MyMapper<Videos> {

    List<VideosVO> queryAllVideos(String videoDesc,  String userId);

    /**
     * 对视频喜欢的数量进行累加
     *
     * @param videoId
     */
    void addVideoListCount(String videoId);

    /**
     * 对视频喜欢的数量进行累减
     *
     * @param videoId
     */
    void reduceVideoListCount(String videoId);

    /**
     * 查询关注的视频
     *
     * @param userId
     * @return
     */
    List<VideosVO> queryMyFollowVideos(String userId);

    /**
     * 查询点赞视频
     *
     * @param userId
     * @return
     */
    List<VideosVO> queryMyLikeVideos(String userId);
}