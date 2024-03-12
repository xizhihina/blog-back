package com.blog.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 拦截器，视图控制器
@Component
public class Config implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;
    @Autowired
    private HtmlInterceptor htmlInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("=================请求已拦截");
        // 拦截所有请求
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/**");
        registry.addInterceptor(htmlInterceptor).addPathPatterns("/backmanage");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 注册视图控制器
        registry.addViewController("permission_error").setViewName("permission_error.html");
        registry.addViewController("backmanage/articleinfo").setViewName("blog.html");
        registry.addViewController("backmanage/usermanagement").setViewName("blog.html");
        registry.addViewController("backmanage/editarticle").setViewName("blog.html");
        registry.addViewController("backmanage").setViewName("blog.html");
        registry.addViewController("").setViewName("redirect:home");
        registry.addViewController("home").setViewName("blog.html");
        registry.addViewController("blog").setViewName("blog.html");
        registry.addViewController("article").setViewName("blog.html");
        registry.addViewController("login").setViewName("blog.html");
        registry.addViewController("register").setViewName("blog.html");
        registry.addViewController("personaldata").setViewName("blog.html");
    }

}
