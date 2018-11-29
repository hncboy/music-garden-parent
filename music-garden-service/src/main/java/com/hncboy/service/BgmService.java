package com.hncboy.service;

import com.hncboy.pojo.Bgm;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/29
 * Time: 9:01
 */
public interface BgmService {

    /**
     * 查询背景音乐列表
     *
     * @return
     */
    List<Bgm> queryBgmList();

    /**
     * 根据id查询bgm信息
     *
     * @param bgmId
     * @return
     */
    Bgm queryBgmById(String bgmId);
}
