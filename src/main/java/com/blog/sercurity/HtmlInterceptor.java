package com.blog.sercurity;

import com.blog.pojo.User;
import com.blog.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HtmlInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 检验请求地址和header中的token信息
        String token = request.getParameter("token");
        if (token==null){
            token=request.getHeader("token");
            if (token==null) {
                response.sendRedirect("/permission_error");
                return false;
            }
        }
        User user=userService.GetUserByToken(token);
        if(user!=null) {
            if (user.getId() == 0) {
                return true;
            }
        }
        // 比如重定向到错误页面
        response.sendRedirect("/permission_error");
        return false;
    }
}