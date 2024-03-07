package com.example.myweb.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MyInfo {
    /**
     * 我的id
     */
    @Id
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
