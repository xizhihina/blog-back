package com.blog.mapper;

import com.blog.pojo.ArticleLanguage;

import java.util.List;


public interface ArticleLanguageMapper {
    List<ArticleLanguage> select(int id);
    boolean delete(int id);

    boolean insert(ArticleLanguage articleLanguage);
}
