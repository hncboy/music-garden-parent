package com.hncboy.controller;

import com.hncboy.pojo.Users;
import com.hncboy.pojo.UsersReport;
import com.hncboy.pojo.vo.PublisherVideo;
import com.hncboy.pojo.vo.UsersVO;
import com.hncboy.service.UserService;
import com.hncboy.utils.JSONResult;
import com.sun.deploy.ui.FancyButton;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/26
 * Time: 18:07
 */
@RestController
@Api(value = "用户相关业务的接口", tags = {"用户相关业务的Controller"})
@RequestMapping("/user")
public class UserController extends BasicController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户上传头像", notes = "用户上传头像的接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
    @PostMapping("/uploadFace")
    public JSONResult uploadFace(String userId, @RequestParam("file") MultipartFile[] files) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("用户id不能为空");
        }

        //文件保存的命名空间
        String fileSpace = "D:/Project/WxxcxProjects/MusicGarden/music-garden-user";
        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/face";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream;

        try {
            if (files != null && files.length > 0) {
                String fileName = files[0].getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    //文件上传的最终保存路径
                    String finalFacePath = fileSpace + uploadPathDB + "/" + fileName;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);

                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }

                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files[0].getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                } else {
                    return JSONResult.errorMsg("上传失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传失败");
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Users user = new Users();
        user.setId(userId);
        user.setFaceImage(uploadPathDB);
        userService.updateUserInfo(user);

        return JSONResult.ok(uploadPathDB);
    }

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息的接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
    @PostMapping("/query")
    public JSONResult query(String userId, String fanId) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("用户id不能为空");
        }

        Users userInfo = userService.queryUserInfo(userId);
        UsersVO userVO = new UsersVO();
        BeanUtils.copyProperties(userInfo, userVO);

        userVO.setFollow(userService.queryIfFollow(userId, fanId));

        return JSONResult.ok(userVO);
    }

    @PostMapping("/queryPublisher")
    public JSONResult queryPublisher(String loginUserId, String videoId, String publishUserId) {
        if (StringUtils.isBlank(publishUserId)) {
            return JSONResult.errorMsg("");
        }

        //1.查询视频发布者信息
        Users userInfo = userService.queryUserInfo(publishUserId);
        UsersVO publisher = new UsersVO();
        BeanUtils.copyProperties(userInfo, publisher);

        //2.查询当前登录者和视频的点赞关系
        boolean userLikeVideo = userService.isUserLikeVideo(loginUserId, videoId);
        PublisherVideo bean = new PublisherVideo();
        bean.setPublisher(publisher);
        bean.setUserLikeVideo(userLikeVideo);

        return JSONResult.ok(bean);
    }

    @PostMapping("/beyourfans")
    public JSONResult beyourfans(String userId, String fanId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(fanId)) {
            return JSONResult.errorMsg("");
        }
        userService.saveUserFanRelation(userId, fanId);
        return JSONResult.ok("关注成功");
    }

    @PostMapping("/dontbeyourfans")
    public JSONResult dontbeyourfans(String userId, String fanId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(fanId)) {
            return JSONResult.errorMsg("");
        }
        userService.deleteUserFanRelation(userId, fanId);
        return JSONResult.ok("取消关注成功");
    }

    @PostMapping("/reportUser")
    public JSONResult reportUser(@RequestBody UsersReport userReport) {
        //保存举报信息
        userService.reportUser(userReport);
        return JSONResult.errorMsg("举报成功！");
    }
}
