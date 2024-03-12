package com.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ArticleInfo{
    /**
     * 文章id
     */
    private Integer id;
    /**
     * 文章名
     */
    private String title;
    /**
     * 文章简介
     */
    private String tabloid;
    /**
     * 发布日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;
    /**
     * 阅读量
     */
    private int readingQuantity;
    public ArticleInfo(int id,String title,String tabloid,Date publishDate,int readingQuantity){
        this.id=id;
        this.title=title;
        this.tabloid=tabloid;
        this.publishDate=publishDate;
        this.readingQuantity=readingQuantity;
    }
}
