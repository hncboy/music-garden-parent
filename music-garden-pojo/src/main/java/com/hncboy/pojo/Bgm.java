package com.hncboy.pojo;

import javax.persistence.Id;

public class Bgm {

    @Id
    private String id;

    /**
     * 作者
     */
    private String author;

    /**
     * 歌名
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取歌名
     *
     * @return name - 歌名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置歌名
     *
     * @param name 歌名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取路径
     *
     * @return path - 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     *
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path;
    }
}