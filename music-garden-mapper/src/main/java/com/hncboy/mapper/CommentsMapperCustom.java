package com.hncboy.mapper;

import com.hncboy.pojo.Comments;
import com.hncboy.pojo.vo.CommentsVO;
import com.hncboy.utils.MyMapper;
import java.util.List;

public interface CommentsMapperCustom extends MyMapper<Comments> {

    List<CommentsVO> queryComments(String videoId);
}