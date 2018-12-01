package com.hncboy.mapper;

import com.hncboy.utils.MyMapper;
import com.hncboy.pojo.SearchRecords;
import java.util.List;


public interface SearchRecordsMapper extends MyMapper<SearchRecords> {

    List<String> getHotWords();
}