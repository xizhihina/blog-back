package com.blog.pojo;

import lombok.Data;
@Data
public class ArticleLanguage {
    /**
     * 文章id
     */
    private Integer id;
    /**
     * 文章语言
     */
    private String language;

    public ArticleLanguage(int id, String language) {
        this.id=id;
        this.language=language;
    }
}
