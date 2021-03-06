package com.hncboy.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/29
 * Time: 11:06
 * <p>
 * 合并短视频和音频文件
 */
public class MergeVideoMp3 {

    private String ffmpegEXE;

    public MergeVideoMp3(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }

    public static void main(String[] args) {
        MergeVideoMp3 ffmpeg = new MergeVideoMp3("D:\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            ffmpeg.convertor("C:\\Users\\hncboy\\Desktop\\test.mp4", "C:\\Users\\hncboy\\Desktop\\test.mp3",
                    "C:\\Users\\hncboy\\Desktop\\test-no-audio.mp4", 5, "C:\\Users\\hncboy\\Desktop\\new.mp4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertor(String videoInputPath, String mp3InputPath, String videoNoAudioPath,
                          double seconds, String videoOutputPath) throws Exception {

        //1.首先去除原视频的音频轨
        firstOperation(videoInputPath, videoNoAudioPath);
        //2.合并去除音频的原视频与bgm
        secondOperation(mp3InputPath, videoNoAudioPath, seconds, videoOutputPath);
    }

    /**
     * 首先去除原视频的音频轨
     *
     * @param videoInputPath
     * @param videoNoAudioPath
     * @throws Exception
     */
    private void firstOperation(String videoInputPath, String videoNoAudioPath) throws Exception {
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-c:v");
        command.add("copy");
        command.add("-an");
        command.add(videoNoAudioPath);
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

    /**
     * 合并去除音频的原视频与bgm
     *
     * @param mp3InputPath
     * @param videoNoAudioPath
     * @param seconds
     * @param videoOutputPath
     * @throws Exception
     */
    private void secondOperation(String mp3InputPath, String videoNoAudioPath,
                                 double seconds, String videoOutputPath) throws Exception {
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoNoAudioPath);
        command.add("-i");
        command.add(mp3InputPath);
        command.add("-t");
        command.add(String.valueOf(seconds));
        command.add("-y");
        command.add(videoOutputPath);

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
}
