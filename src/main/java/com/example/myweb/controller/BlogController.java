package com.example.myweb.controller;

import com.example.myweb.param.ArticleParam;
import com.example.myweb.param.IntParam;
import com.example.myweb.pojo.ArticleInfo;
import com.example.myweb.pojo.MyInfo;
import com.example.myweb.sercurity.VerifyAdminToken;
import com.example.myweb.service.impl.BlogServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/blog")
public class BlogController {
    //@Resource
    @Autowired
    private BlogServiceImpl blogService;

    @GetMapping("/my_info")
    public MyInfo GetMyInfo(){return blogService.GetMyInfo();}

    @VerifyAdminToken
    @PostMapping("/my_info")
    public boolean SetMyInfo(@RequestBody MyInfo myInfo){return blogService.SetMyInfo(myInfo);}

    @GetMapping("/article/info")
    public Iterable<ArticleInfo> GetAllArticleInfo(){return blogService.GetAllArticleInfo();}

    @GetMapping("/article/info/{id}")
    public ArticleInfo GetArticleInfo(@PathVariable int id){
        return blogService.GetArticleInfoById(id);
    }

    @GetMapping("/article/content/{id}")
    public String GetArticleContent(@PathVariable int id){
        if(blogService.GetArticleContentById(id)!=null)return blogService.GetArticleContentById(id).getContent();
        else return "";
    }

    @GetMapping("/article/language/{id}")
    public List<String> GetArticleLanguage(@PathVariable int id){
        return blogService.GetArticleLanguageById(id);
    }

    @VerifyAdminToken
    @PostMapping("/article")
    public boolean SetArticle(@RequestBody ArticleParam articleParam){return blogService.SetOrCreateArticle(articleParam);}

    @VerifyAdminToken
    @DeleteMapping("/article/{id}")
    public boolean DeleteArticle(@PathVariable int id){
        return blogService.DeleteArticle(id);
    }

    @GetMapping("/article/add_visit_count/{id}")
    public boolean AddArticleVisitCount(@PathVariable int id){
        return blogService.AddArticleVisitCount(id);
    }

    @GetMapping("/visit_count")
    public int GetVisitCount(){
        return blogService.GetVisitCount();
    }

    @VerifyAdminToken
    @GetMapping("/add_visit_count")
    public boolean AddVisitCount(){return blogService.AddVisitCount();}

    @VerifyAdminToken
    @PostMapping("/visit_count")
    public boolean SetVisitCount(@RequestBody IntParam count){
        return blogService.SetVisitCount(count.get_int());
    }

    @GetMapping("/article_count_for_each_language/{language}")
    public int GetArticleCountForEachLanguage(@PathVariable String language){return blogService.GetArticleCountForEachLanguage(language);}

    @VerifyAdminToken
    @GetMapping("/add_article_count")
    public boolean AddArticleCount(){return blogService.AddArticleCount();}

    @VerifyAdminToken
    @GetMapping("/reduce_article_count")
    public boolean ReduceArticleCount(){return blogService.RedArticleCount();}
}
