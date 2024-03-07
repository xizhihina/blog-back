package com.example.myweb.service;

import com.example.myweb.param.ChangePasswordParam;
import com.example.myweb.param.LoginParam;
import com.example.myweb.param.UserParam;
import com.example.myweb.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 通过用户Id获取用户信息
     *
     * @param id
     * @return
     */
    User GetUserById(int id);

    /**
     * 登录
     * @param loginParam
     * @return token字符串
     */
    String Login(LoginParam loginParam);

    /**
     * 注册
     * @param loginParam
     * @return token字符串
     */
    String Register(LoginParam loginParam);

    String ChangePassword(ChangePasswordParam changePasswordParam);
    /**
     * 验证token是否过期
     * @param token
     * @return 过期为true，未过期为false
     */
    boolean isExpiration(String token);

    /**
     * 通过token确定用户
     * @param token
     * @return
     */
    User GetUserByToken(String token);

    /**
     * 获取所有用户
     * @return
     */
    List<UserParam> GetAllUser();

    /**
     * 删除用户
     */
    boolean DeleteUserById(int id);
    boolean SetUser(User user);
}
