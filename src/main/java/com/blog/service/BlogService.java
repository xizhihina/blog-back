package com.blog.service;

import com.blog.param.ArticleParam;
import com.blog.pojo.ArticleContent;
import com.blog.pojo.ArticleInfo;
import com.blog.pojo.MyInfo;

import java.util.List;

public interface BlogService {

    /**
     * 获取所有文章信息
     * @return
     */
    Iterable<ArticleInfo> GetAllArticleInfo();

    /**
     * 通过文章id返回文章
     * @param id
     * @return
     */
    ArticleInfo GetArticleInfoById(int id);

    /**
     * 通过文章Id返回该篇文章所使用的语言
     * @param id
     * @return
     */
    List<String> GetArticleLanguageById(int id);

    ///**
    // * 通过文章所使用的语言返回使用该语言的文章数量
    // * @param language
    // * @return
    // */
    //int GetArticleCountForEachLanguage(String language);

    /**
     * 获取我的信息
     * @return
     */
    MyInfo GetMyInfo();

    /**
     * 获取阅读量
     * @return
     */
    int GetVisitCount();

    /**
     * 通过文章Id获取文章内容
     * @param id
     * @return
     */
    ArticleContent GetArticleContentById(int id);


    ///**
    // * 更改我的信息
    // * @param myInfo
    // * @return
    // */
    //boolean SetMyInfo(MyInfo myInfo);

    ///**
    // * 更改阅读量
    // * @param count
    // * @return
    // */
    //boolean SetVisitCount(int count);

    /**
     * 更改文章
     * @param articleParam
     * @return
     */
    boolean SetOrCreateArticle(ArticleParam articleParam);

    /**
     * 阅读量++
     * @return
     */
    boolean AddVisitCount();

    /**
     * 增加文章阅读次数
     * @return
     */
    boolean AddArticleVisitCount(int id);
    /**
     * 删除文章
     * @param id
     * @return
     */
    boolean DeleteArticle(int id);
    /**
     * 文章数++
     * @return
     */
    boolean AddArticleCount();
    /**
     * 文章数--
     * @return
     */
    boolean RedArticleCount();
}
