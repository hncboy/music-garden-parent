package com.hncboy.service;

import com.hncboy.pojo.Users;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/26
 * Time: 20:41
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     *
     * @param username
     * @return
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUser(Users user);

    /**
     * 用户登录-根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    Users queryUserForLogin(String username, String password);

    /**
     * 用户修改信息
     *
     * @param user
     */
    void updateUserInfo(Users user);

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    Users queryUserInfo(String userId);
}
