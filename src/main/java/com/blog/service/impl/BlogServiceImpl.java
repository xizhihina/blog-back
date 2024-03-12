package com.blog.service.impl;

import com.blog.mapper.*;
import com.blog.param.ArticleParam;
import com.blog.pojo.ArticleContent;
import com.blog.pojo.ArticleInfo;
import com.blog.pojo.ArticleLanguage;
import com.blog.pojo.MyInfo;
import com.blog.service.BlogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private ArticleInfoMapper articleInfoMapper;
    @Resource
    private ArticleLanguageMapper articleLanguageMapper;
    @Resource
    private MyInfoMapper myInfoMapper;
    @Resource
    private ArticleContentMapper articleContentMapper;
    @Resource
    private VisitCountMapper visitCountMapper;

    @Override
    public List<ArticleInfo> GetAllArticleInfo(){
        return articleInfoMapper.selectAll();
    }
    @Override
    public ArticleInfo GetArticleInfoById(int id){
        return articleInfoMapper.select(id);
    }

    @Override
    public List<String> GetArticleLanguageById(int id){
        List<String> list=new ArrayList<>();
        for (ArticleLanguage articleLanguage : articleLanguageMapper.select(id)){
            list.add(articleLanguage.getLanguage());
        }
        return list;
    }

    //@Override
    //public int GetArticleCountForEachLanguage(String language){
    //    String sql= "select count(*) as language_count from article_language where language='" +language+ "'";
    //    return jdbcTemplate.query(sql,(ResultSet rs,int rownum)->{return rs.getInt("language_count");}).get(0);
    //}

    @Override
    public MyInfo GetMyInfo(){
        return myInfoMapper.select();
    }

    //@Override
    //public boolean SetMyInfo(MyInfo myInfo){
    //    myInfoRepository.save(myInfo);
    //    return true;
    //}

    @Override
    public int GetVisitCount(){
        return  visitCountMapper.select();
    }

    //@Override
    //public boolean SetVisitCount(int count){//visitCount=count
    //    jdbcTemplate.update("update visit_count set count="+count);
    //    return true;
    //}

    @Override
    public boolean AddVisitCount(){//visitCount++
        return visitCountMapper.add();
    }

    @Override
    public ArticleContent GetArticleContentById(int id){
        return articleContentMapper.select(id);
    }

    /**
     * &#064;Transactional保证事务性
     * articleParam.Getxxx中，如果有任何xxx类型所需参数为null，则获取到的xxx为null
     * 三个Set处理时遇到null不作任何数据库处理，直接返回false
     * 三个false则返回false，否则返回true
     */
    @Transactional
    @Override
    public boolean SetOrCreateArticle(ArticleParam articleParam) {
        SetArticleLanguage(articleParam.getId(), articleParam.getArticleLanguage());
        if(SetArticleInfo(articleParam.GetArticleInfo()) && SetArticleContent(articleParam.GetArticleContent())){
            return true;
        }
        else {
            return CreateArticleInfo(articleParam.GetArticleInfo()) && CreateArticleContent(articleParam.GetArticleContent());
        }
    }

    @Override
    public boolean DeleteArticle(int id) {
        articleContentMapper.delete(id);
        SetArticleLanguage(id,new ArrayList<>());
        articleInfoMapper.delete(id);
        return true;
    }

    @Override
    public boolean AddArticleCount() {
        MyInfo myInfo=myInfoMapper.select();
        myInfo.setArticleCount(myInfo.getArticleCount()+1);
        myInfoMapper.update(myInfo);
        return true;
    }

    @Override
    public boolean RedArticleCount() {
        MyInfo myInfo=myInfoMapper.select();
        myInfo.setArticleCount(myInfo.getArticleCount()-1);
        if(myInfo.getArticleCount()<0)myInfo.setArticleCount(0);
        myInfoMapper.update(myInfo);
        return true;
    }

    @Override
    public boolean AddArticleVisitCount(int id) {
        ArticleInfo articleInfo=articleInfoMapper.select(id);
        articleInfo.setReadingQuantity(articleInfo.getReadingQuantity()+1);
        return true;
    }

    private boolean SetArticleInfo(ArticleInfo articleInfo){
        if(articleInfo==null)return false;
        if (articleInfo.getId()==null)return false;
        return articleInfoMapper.update(articleInfo);
    }
    private boolean SetArticleContent(ArticleContent articleContent){
        if(articleContent==null)return false;
        if(articleContent.getId()==null)return false;
        return articleContentMapper.update(articleContent);
    }
    private boolean CreateArticleInfo(ArticleInfo articleInfo){
        if(articleInfo==null)return false;
        if (articleInfo.getId()==null)return false;
        return articleInfoMapper.insert(articleInfo);
    }
    private boolean CreateArticleContent(ArticleContent articleContent){
        if(articleContent==null)return false;
        if(articleContent.getId()==null)return false;
        return articleContentMapper.add(articleContent);
    }
    private boolean SetArticleLanguage(int id,List<String> languages){
        if(languages==null)return false;
        articleLanguageMapper.delete(id);//删除原来该文章的所有language
        for (String language :languages){//逐个插入
            articleLanguageMapper.insert(new ArticleLanguage(id,language));
        }
        return true;
    }
    //private RowMapper<ArticleLanguage> GetArticleLanguageMapper(){
    //    return (ResultSet rs, int rownum)->{
    //        ArticleLanguage articleLanguage=new ArticleLanguage();
    //        articleLanguage.setId(rs.getInt("id"));
    //        articleLanguage.setArticleLanguage(rs.getString("language"));
    //        return articleLanguage;
    //    };
    //}
}
