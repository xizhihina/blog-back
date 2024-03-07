package com.example.myweb.service.impl;

import com.example.myweb.param.ArticleParam;
import com.example.myweb.pojo.ArticleContent;
import com.example.myweb.pojo.ArticleInfo;
import com.example.myweb.pojo.ArticleLanguage;
import com.example.myweb.pojo.MyInfo;
import com.example.myweb.repository.ArticleContentRepository;
import com.example.myweb.repository.ArticleInfoRepository;
import com.example.myweb.repository.MyInfoRepository;
import com.example.myweb.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ArticleInfoRepository articleInfoRepository;
    @Autowired
    private MyInfoRepository myInfoRepository;
    @Autowired
    private ArticleContentRepository articleContentRepository;

    @Override
    public Iterable<ArticleInfo> GetAllArticleInfo(){
        return articleInfoRepository.findAll();
    }
    @Override
    public ArticleInfo GetArticleInfoById(int id){
        return articleInfoRepository.findById(id).isPresent()?articleInfoRepository.findById(id).get():null;
    }

    @Override
    public List<String> GetArticleLanguageById(int id){
        String sql="select id,language from article_language where id="+ id;
        List<String> list=new ArrayList<>();
        for (ArticleLanguage articleLanguage : jdbcTemplate.query(sql,GetArticleLanguageMapper())){
            list.add(articleLanguage.getArticleLanguage());
        }
        return list;
    }

    @Override
    public int GetArticleCountForEachLanguage(String language){
        String sql= "select count(*) as language_count from article_language where language='" +language+ "'";
        return jdbcTemplate.query(sql,(ResultSet rs,int rownum)->{return rs.getInt("language_count");}).get(0);
    }

    @Override
    public MyInfo GetMyInfo(){return myInfoRepository.findAll().iterator().next();}

    @Override
    public boolean SetMyInfo(MyInfo myInfo){
        myInfoRepository.save(myInfo);
        return true;
    }

    @Override
    public int GetVisitCount(){
        return jdbcTemplate.query("select * from visit_count",(ResultSet rs, int rownum)->{return rs.getInt("count");}).get(0);
    }

    @Override
    public boolean SetVisitCount(int count){//visitCount=count
        jdbcTemplate.update("update visit_count set count="+count);
        return true;
    }

    @Override
    public boolean AddVisitCount(){//visitCount++
        jdbcTemplate.update("update visit_count set count=count+1");
        return true;
    }

    @Override
    public ArticleContent GetArticleContentById(int id){
        return articleContentRepository.findById(id).isPresent()?articleContentRepository.findById(id).get():null;
    }

    /**
     * @Transactional保证事务性
     * articleParam.Getxxx中，如果有任何xxx类型所需参数为null，则获取到的xxx为null
     * 三个Set处理时遇到null不作任何数据库处理，直接返回false
     * 三个false则返回false，否则返回true
     */
    @Transactional
    @Override
    public boolean SetOrCreateArticle(ArticleParam articleParam) {
        return SetArticleInfo(articleParam.GetArticleInfo()) &&
                SetArticleContent(articleParam.GetArticleContent()) &&
                SetArticleLanguage(articleParam.getId(), articleParam.getArticleLanguage());
    }

    @Override
    public boolean DeleteArticle(int id) {
        articleContentRepository.deleteById(id);
        SetArticleLanguage(id,new ArrayList<>());
        articleInfoRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean AddArticleCount() {
        MyInfo myInfo=myInfoRepository.findAll().iterator().next();
        myInfo.setArticleCount(myInfo.getArticleCount()+1);
        myInfoRepository.save(myInfo);
        return true;
    }

    @Override
    public boolean RedArticleCount() {
        MyInfo myInfo=myInfoRepository.findAll().iterator().next();
        myInfo.setArticleCount(myInfo.getArticleCount()-1);
        if(myInfo.getArticleCount()<0)myInfo.setArticleCount(0);
        myInfoRepository.save(myInfo);
        return true;
    }

    @Override
    public boolean AddArticleVisitCount(int id) {
        jdbcTemplate.update("update article_info set reading_quantity=reading_quantity+1 where id="+id);
        return true;
    }

    private boolean SetArticleInfo(ArticleInfo articleInfo){
        if(articleInfo==null)return false;
        if (articleInfo.getId()==null)return false;
        articleInfo.setInsert(GetArticleInfoById(articleInfo.getId()) == null);//没找到，则为insert的内容
        articleInfoRepository.save(articleInfo);
        return true;
    }
    private boolean SetArticleContent(ArticleContent articleContent){
        if(articleContent==null)return false;
        if(articleContent.getId()==null)return false;
        articleContent.setInsert(GetArticleContentById(articleContent.getId())==null);
        articleContentRepository.save(articleContent);
        return true;
    }
    private boolean SetArticleLanguage(int id,List<String> languages){
        if(languages==null)return false;
        jdbcTemplate.update("delete from article_language where id="+id);//删除原来该文章的所有language
        for (String language :languages){//逐个插入
            jdbcTemplate.update("insert into article_language values ("+id+",'"+language+"')");
        }
        return true;
    }
    private RowMapper<ArticleLanguage> GetArticleLanguageMapper(){
        return (ResultSet rs, int rownum)->{
            ArticleLanguage articleLanguage=new ArticleLanguage();
            articleLanguage.setId(rs.getInt("id"));
            articleLanguage.setArticleLanguage(rs.getString("language"));
            return articleLanguage;
        };
    }
}
