package com.blog.controller;

import com.blog.param.ChangePasswordParam;
import com.blog.param.UserParam;
import com.blog.pojo.User;
import com.blog.sercurity.VerifyAdminToken;
import com.blog.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public UserParam GetUserByIdWithoutPasswordToken(@PathVariable int id){return userService.GetUserById(id).GetUserWithoutPasswordToken();}


    @VerifyAdminToken
    @GetMapping("")
    public List<UserParam> GetAllUser(){return userService.GetAllUser();};

    @VerifyAdminToken
    @DeleteMapping("/{id}")
    public boolean DeleteUserById(@PathVariable int id){return userService.DeleteUserById(id);};

    /**
     * 1从请求token中获取对应用户
     * 2无匹配token返回
     * 3管理者等级不允许更改
     * 4更改自身数据或管理员更改允许
     */
    @PostMapping("")
    public boolean SetUser(HttpServletRequest request,@RequestBody User user){
        User _user=userService.GetUserByToken(request.getHeader("token"));
        if (_user==null)return false;
        if (user.getId()==0)user.setLevel(6);
        if (user.getId()==_user.getId()||_user.getLevel()==6)
            return userService.SetUser(user);
        else return false;
    };

    @PostMapping("/change_password")
    public String ChangePassword(@RequestBody ChangePasswordParam changePasswordParam){
        return userService.ChangePassword(changePasswordParam);
    }
}
