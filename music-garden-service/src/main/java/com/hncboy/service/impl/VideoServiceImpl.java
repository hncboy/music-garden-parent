package com.hncboy.service.impl;

import com.hncboy.mapper.VideosMapper;
import com.hncboy.pojo.Videos;
import com.hncboy.service.VideoService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/29
 * Time: 14:15
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideosMapper videosMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String saveVideo(Videos video) {
        String id = sid.nextShort();
        video.setId(id);
        videosMapper.insertSelective(video);
        return id;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateVideo(String videoId, String coverPath) {
        Videos video = new Videos();
        video.setId(videoId);
        video.setCoverPath(coverPath);
        videosMapper.updateByPrimaryKeySelective(video);
    }
}
