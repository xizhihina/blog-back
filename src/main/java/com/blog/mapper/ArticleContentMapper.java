package com.blog.mapper;

import com.blog.pojo.ArticleContent;


public interface ArticleContentMapper {
    ArticleContent select(int id);
    boolean delete(int id);

    boolean update(ArticleContent articleContent);

    boolean add(ArticleContent articleContent);
}
