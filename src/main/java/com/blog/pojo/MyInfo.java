package com.blog.pojo;

import lombok.Data;

@Data
public class MyInfo {
    /**
     * 我的id
     */
    private int id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 技术栈
     */
    private String skill;
    /**
     * 文章数
     */
    private int articleCount;
}
