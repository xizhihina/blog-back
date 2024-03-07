package com.example.myweb.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ArticleInfo implements Persistable<Integer> {
    /**
     * 文章id
     */
    @Id
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

    @Transient
    @JsonIgnore
    private boolean isInsert;

    @Override
    @JsonIgnore
    public boolean isNew() {
        return isInsert;
    }
}
