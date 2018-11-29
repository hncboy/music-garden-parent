package com.hncboy.enums;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/29
 * Time: 14:08
 */
public enum VideoStatusEnum {

    SUCCESS(1), //发布成功
    FORBID(2); //禁止播放，管理员操作

    public final int value;

    VideoStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
