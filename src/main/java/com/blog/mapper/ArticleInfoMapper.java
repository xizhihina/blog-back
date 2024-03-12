package com.blog.mapper;

import com.blog.pojo.ArticleContent;
import com.blog.pojo.ArticleInfo;

import java.util.List;


public interface ArticleInfoMapper {
    ArticleInfo select(int id);
    List<ArticleInfo> selectAll();
    boolean delete(int id);

    boolean update(ArticleInfo articleInfo);

    boolean insert(ArticleInfo articleInfo);
}
