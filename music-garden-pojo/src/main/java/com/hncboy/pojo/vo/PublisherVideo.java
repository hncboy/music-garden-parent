package com.hncboy.pojo.vo;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/12/1
 * Time: 17:02
 */
public class PublisherVideo {

    private UsersVO publisher;
    private boolean userLikeVideo;

    public UsersVO getPublisher() {
        return publisher;
    }

    public void setPublisher(UsersVO publisher) {
        this.publisher = publisher;
    }

    public boolean isUserLikeVideo() {
        return userLikeVideo;
    }

    public void setUserLikeVideo(boolean userLikeVideo) {
        this.userLikeVideo = userLikeVideo;
    }
}
