package com.blog.pojo;

import lombok.Data;

@Data
public class ArticleContent{
    //@Id
    private Integer id;

    private String content;

    public ArticleContent(int id, String content) {
        this.id=id;
        this.content=content;
    }
}
