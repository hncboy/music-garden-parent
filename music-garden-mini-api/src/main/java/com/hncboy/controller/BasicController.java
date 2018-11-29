package com.hncboy.controller;

import com.hncboy.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/27
 * Time: 14:26
 */
@RestController
public class BasicController {

    public static final String USER_REDIS_SESSION = "user-redis-session";

    @Autowired
    public RedisOperator redis;

    //文件保存的命名空间
    public static final String FILE_SPACE = "D:/Project/WxxcxProjects/MusicGarden/music-garden-user";

    //ffmpeg所在目录
    public static final String FFMPEG_EXE ="D:\\ffmpeg\\bin\\ffmpeg.exe";

}
