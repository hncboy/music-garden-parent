package com.hncboy.controller;

import com.hncboy.enums.VideoStatusEnum;
import com.hncboy.pojo.Bgm;
import com.hncboy.pojo.Comments;
import com.hncboy.pojo.Videos;
import com.hncboy.service.BgmService;
import com.hncboy.service.VideoService;
import com.hncboy.utils.FetchVideoCover;
import com.hncboy.utils.JSONResult;
import com.hncboy.utils.MergeVideoMp3;
import com.hncboy.utils.PagedResult;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/29
 * Time: 9:36
 */
@RestController
@Api(value = "视频相关业务的接口", tags = {"视频相关业务的Controller"})
@RequestMapping("/video")
public class VideoController extends BasicController {

    @Autowired
    private BgmService bgmService;

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "上传视频", notes = "上传视频的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "bgmId", value = "背景音乐id",
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoSeconds", value = "背景音乐播放长度", required = true,
                    dataType = "double", paramType = "form"),
            @ApiImplicitParam(name = "videoWidth", value = "视频宽度", required = true,
                    dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "videoHeight", value = "视频高度", required = true,
                    dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "desc", value = "视频描述",
                    dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public JSONResult upload(String userId, String bgmId, double videoSeconds, int videoWidth,
                             int videoHeight, String desc,
                             @ApiParam(value = "短视频", required = true) MultipartFile file) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("用户id不能为空");
        }

        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/video";
        String coverPathDB = "/" + userId + "/video";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream;
        //文件上传的最终保存路径
        String finalVideoPath = "";

        try {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                String fileNamePrefix = fileName.split("\\.")[0];

                if (StringUtils.isNotBlank(fileName)) {
                    finalVideoPath = FILE_SPACE + uploadPathDB + "/" + fileName;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);
                    coverPathDB = coverPathDB + "/" + fileNamePrefix + ".jpg";

                    File outFile = new File(finalVideoPath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }

                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
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

        //判断bgmId是否为空
        if (StringUtils.isNotBlank(bgmId)) {
            Bgm bgm = bgmService.queryBgmById(bgmId);
            String mp3InputPath = FILE_SPACE + bgm.getPath();

            MergeVideoMp3 tool = new MergeVideoMp3(FFMPEG_EXE);
            //原video
            String videoInputPath = finalVideoPath;
            //最终输出的video
            String videoOutputName = UUID.randomUUID().toString() + ".mp4";
            //没有声音的原video
            String videoNoAudioName = UUID.randomUUID().toString() + ".mp4";
            uploadPathDB = "/" + userId + "/video/" + videoOutputName;
            finalVideoPath = FILE_SPACE + uploadPathDB;
            String videoNoAudioPath = FILE_SPACE + "/" + userId + "/video/" + videoNoAudioName;

            System.out.println("videoInputPath = " + videoInputPath);
            System.out.println("finalVideoPath = " + finalVideoPath);
            System.out.println("videoNoAudioPath = " + videoNoAudioPath);

            tool.convertor(videoInputPath, mp3InputPath, videoNoAudioPath, videoSeconds, finalVideoPath);
        }

        //对视频截图
        FetchVideoCover videoInfo = new FetchVideoCover(FFMPEG_EXE);
        try {
            videoInfo.getCover(finalVideoPath, FILE_SPACE + coverPathDB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //保存视频信息到数据库
        Videos video = new Videos();
        video.setAudioId(bgmId);
        video.setUserId(userId);
        video.setVideoSeconds((float) videoSeconds);
        video.setVideoHeight(videoHeight);
        video.setVideoWidth(videoWidth);
        video.setVideoDesc(desc);
        video.setVideoPath(uploadPathDB);
        video.setCoverPath(coverPathDB);
        video.setStatus(VideoStatusEnum.SUCCESS.value);
        video.setCreateTime(new Date());

        String videoId = videoService.saveVideo(video);

        return JSONResult.ok(videoId);
    }

    @ApiOperation(value = "上传封面", notes = "上传封面的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoId", value = "视频主键id", required = true,
                    dataType = "String", paramType = "form")
    })
    @PostMapping(value = "/uploadCover", headers = "content-type=multipart/form-data")
    public JSONResult uploadCover(String userId, String videoId, @ApiParam(value = "视频封面", required = true) MultipartFile file) throws Exception {
        if (StringUtils.isBlank(videoId) || StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("视频主键id和用户id不能为空");
        }

        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/video";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream;
        //文件上传的最终保存路径
        String finalCoverPath;

        try {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    finalCoverPath = FILE_SPACE + uploadPathDB + "/" + fileName;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);

                    File outFile = new File(finalCoverPath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }

                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
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

        videoService.updateVideo(videoId, uploadPathDB);

        return JSONResult.ok();
    }

    /**
     * 分页和搜索查询视频列表
     * isSaveRecord: 1-需要保存     0-不需要保存，或者为空
     *
     * @param video
     * @param isSaveRecord
     * @param page
     * @return
     */
    @PostMapping(value = "/showAll")
    public JSONResult showAll(@RequestBody Videos video, Integer isSaveRecord,
                              Integer page, Integer pageSize) {

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedResult result = videoService.getAllVideos(video, isSaveRecord, page, pageSize);
        return JSONResult.ok(result);
    }

    @PostMapping("/hot")
    public JSONResult hot() {
        return JSONResult.ok(videoService.getHotWords());
    }

    @PostMapping("/userLike")
    public JSONResult userLike(String userId, String videoId, String videoCreatorId) {
        videoService.userLikeVideo(userId, videoId, videoCreatorId);
        return JSONResult.ok();
    }

    @PostMapping("/userUnLike")
    public JSONResult userUnLike(String userId, String videoId, String videoCreatorId) {
        videoService.userUnLikeVideo(userId, videoId, videoCreatorId);
        return JSONResult.ok();
    }

    /**
     * 我关注的人发的视频
     *
     * @param userId
     * @param page
     * @return
     */
    @PostMapping("/showMyFollow")
    public JSONResult showMyFollow(String userId, Integer page) {

        if (StringUtils.isBlank(userId)) {
            return JSONResult.ok();
        }

        if (page == null) {
            page = 1;
        }
        int pageSize = 6;
        PagedResult videosList = videoService.queryMyFollowVideos(userId, page, pageSize);

        return JSONResult.ok(videosList);
    }

    /**
     * 我收藏(点赞)过的视频列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @PostMapping("/showMyLike")
    public JSONResult showMyLike(String userId, Integer page, Integer pageSize) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.ok();
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 6;
        }

        PagedResult videosList = videoService.queryMyLikeVideos(userId, page, pageSize);

        return JSONResult.ok(videosList);
    }

    @PostMapping("/saveComment")
    public JSONResult saveComment(@RequestBody Comments comment, String fatherCommentId, String toUserId) {
        comment.setFatherCommentId(fatherCommentId);
        comment.setToUserId(toUserId);
        videoService.saveComment(comment);
        return JSONResult.ok();
    }

    @PostMapping("/getVideoComments")
    public JSONResult getVideoComments(String videoId, Integer page, Integer pageSize) {
        if (StringUtils.isBlank(videoId)) {
            return JSONResult.ok();
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        PagedResult list = videoService.getAllComments(videoId, page, pageSize);
        return JSONResult.ok(list);
    }
}
