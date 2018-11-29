package com.hncboy.controller;

import com.hncboy.service.BgmService;
import com.hncboy.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/29
 * Time: 9:05
 */
@RestController
@Api(value = "背景音乐的接口", tags = {"背景音乐的Controller"})
@RequestMapping("/bgm")
public class BgmController {

    @Autowired
    private BgmService bgmService;

    @ApiOperation(value = "获取背景音乐列表", notes = "获取背景音乐列表的接口")
    @PostMapping("/list")
    public JSONResult list() {
        return JSONResult.ok(bgmService.queryBgmList());
    }
}
