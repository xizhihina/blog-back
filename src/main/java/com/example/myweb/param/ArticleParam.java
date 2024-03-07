package com.example.myweb.param;

import com.example.myweb.pojo.ArticleContent;
import com.example.myweb.pojo.ArticleInfo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 用于创建和更改文章
 */
@Data
public class ArticleParam {
    private Integer id;
    private String title;
    private String tabloid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;
    private Integer readingQuantity;
    private List<String> articleLanguage;
    private String content;
    public ArticleInfo GetArticleInfo(){
        if(id==null||title==null||tabloid==null||publishDate==null||readingQuantity==null)return null;
        return new ArticleInfo(id,title,tabloid,publishDate,readingQuantity);
    }
    public ArticleContent GetArticleContent(){
        if(id==null||content==null)return null;
        return new ArticleContent(id,content);
    }
}
