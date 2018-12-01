package com.hncboy.mapper;

import com.hncboy.pojo.Videos;
import com.hncboy.pojo.vo.VideosVO;
import com.hncboy.utils.MyMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideosMapperCustom extends MyMapper<Videos> {

    List<VideosVO> queryAllVideos(@Param("videoDesc") String videoDesc);
}