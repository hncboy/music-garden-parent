package com.hncboy.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/26
 * Time: 20:37
 * <p>
 * 对字符串进行md5加密
 */
public class MD5Utils {

    public static String getMD5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest(strValue.getBytes()));
    }
}
