package com.blog.controller;

import com.blog.param.LoginParam;
import com.blog.service.UserService;
import com.blog.valid.LoginValidator;
import jakarta.annotation.Resource;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LoginRegisterController {
    @Resource
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        //绑定验证器
        binder.setValidator(new LoginValidator());
    }

    @PostMapping("/login")
    public String Login(@RequestBody LoginParam loginParam){
        return userService.Login(loginParam);
    }

    @PostMapping("/register")
    public String Register(@Validated @RequestBody LoginParam loginParam, Errors errors){
        // 数据验证
        if(errors.hasErrors()){
            StringBuilder log= new StringBuilder();
            List<ObjectError> oes=errors.getAllErrors();
            for(ObjectError oe:oes){
                if(oe instanceof FieldError fe){
                    //字段错误
//                    FieldError fe=(FieldError) oe;
                    log.append(fe.getDefaultMessage()).append("\n");
                }else {
                    //对象错误
                    log.append(oe.getDefaultMessage()).append("\n");
                }
            }
            return log.toString();
        }
        return userService.Register(loginParam);
    }
}
