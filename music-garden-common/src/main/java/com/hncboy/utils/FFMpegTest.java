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
 * Time: 10:37
 */
public class FFMpegTest {

    private String ffmpegEXE;

    public FFMpegTest(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }

    public static void main(String[] args) {
        FFMpegTest ffmpeg = new FFMpegTest("D:\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            ffmpeg.convertor("C:\\Users\\hncboy\\Desktop\\test.mp4", "C:\\Users\\hncboy\\Desktop\\test.avi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertor(String videoInputPath, String videoOutputPath) throws Exception {
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add(videoOutputPath);
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ((line = br.readLine()) != null) {

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
