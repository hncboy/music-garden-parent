package com.hncboy.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/29
 * Time: 15:34
 * <p>
 * 截取视频第1s第1帧的图片
 */
public class FetchVideoCover {

    // 视频路径
    private String ffmpegEXE;

    public FetchVideoCover() {

    }

    public FetchVideoCover(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }

    public static void main(String[] args) {
        // 获取视频信息。
        FetchVideoCover videoInfo = new FetchVideoCover("D:\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            videoInfo.getCover("D:\\ffmpeg\\bin\\test.mp4", "D:\\ffmpeg\\bin\\test.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCover(String videoInputPath, String coverOutputPath) throws Exception {
        List<String> command = new java.util.ArrayList<>();
        command.add(ffmpegEXE);
        //第1秒
        command.add("-ss");
        command.add("00:00:01");

        command.add("-y");
        command.add("-i");
        command.add(videoInputPath);
        // 第1帧
        command.add("-vframes");
        command.add("1");
        command.add(coverOutputPath);


        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        while (br.readLine() != null) {
        }

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
    }

    public String getFfmpegEXE() {
        return ffmpegEXE;
    }

    public void setFfmpegEXE(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }
}
