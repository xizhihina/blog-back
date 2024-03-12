package com.blog.service.impl;

import com.blog.mapper.UserMapper;
import com.blog.param.ChangePasswordParam;
import com.blog.param.LoginParam;
import com.blog.param.UserParam;
import com.blog.pojo.User;
import com.blog.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.ResultSet;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User GetUserById(int id){
        return userMapper.select(id);
    }

    @Override
    public String Login(LoginParam loginParam){
        return LoginOrRegisterReturnToken(loginParam.getId(),loginParam.getPassword(),false);
    }

    @Override
    public String Register(LoginParam loginParam){
        return LoginOrRegisterReturnToken(loginParam.getId(),loginParam.getPassword(),true);
    }


    @Override
    public String ChangePassword(ChangePasswordParam changePasswordParam) {
        User user=GetUserById(changePasswordParam.getId());
        if(user==null|| !Objects.equals(user.getPasswordMd5(), changePasswordParam.getPassword_init()))return "";
        String passwordMd5= DigestUtils.md5DigestAsHex(changePasswordParam.getPassword_final().getBytes());
        user.setPasswordMd5(passwordMd5);
        String token=CreateToken(user.getId(), user.getPasswordMd5());
        user.setToken(token);
        userMapper.update(user);
        return token;
    }


    @Override
    public boolean isExpiration(String token) {
        Claims claims = parseToken(token);
        Date expireTime = claims.getExpiration();
        return expireTime.compareTo(new Date()) < 0;
    }

    @Override
    public User GetUserByToken (String token){
        return userMapper.selectByToken(token);
    }

    @Override
    public List<UserParam> GetAllUser(){
        List<User> userList=userMapper.selectAll();
        List<UserParam> userParamList = new ArrayList<>();
        for (User user : userList) {
            userParamList.add(user.GetUserWithoutPasswordToken());
        }
        return userParamList;
    }


    @Override
    public boolean DeleteUserById(int id) {
        userMapper.delete(id);
        return true;
    }

    @Override
    public boolean SetUser(User user){
        User _user=userMapper.select(user.getId());
        if (_user==null)return false;
        user.setPasswordMd5(_user.getPasswordMd5());
        user.setToken(_user.getToken());
        userMapper.update(user);
        return true;
    }

    /**
     * 查找id对应的密码，正确返回新的token，错误返回密码错误
     * @param model 0为登录，1为注册
     * @return token
     */
    private String LoginOrRegisterReturnToken(int id,String password,boolean model){
        String passwordMd5= DigestUtils.md5DigestAsHex(password.getBytes());
        User user=GetUserById(id);
        if(model) {
            if (user!=null)return "用户名重复";
            user = new User(id, passwordMd5);
        }
        if(user==null) return "不存在该用户";
        if(model || Objects.equals(user.getPasswordMd5(), passwordMd5)){
            String token= CreateToken(user.getId(),user.getPasswordMd5());
            //写入token到user_token
            user.setToken(token);
            //user.setInsert(model);
            if(user.getId()==0)userMapper.update(user);//jdbcTemplate.update("update user set token=? where id =0;",token);
            else userMapper.insert(user);//id为0或null时，判断为新纪录，执行插入（这里data-jdbc和data-jpa不一样）
            return token;
        }
        return "密码错误";
    };
    //生成token
    private String CreateToken(int id, String passwordMd5) {
        String tokenSignKey="xizhihinaForeverSukiHIMEHINAandZhandoubagejiANDtianyinbifang";
        Date expire_time=new Date(new Date().getTime()+1000 * 60 * 60 * 24 * 20); // 20天过期
        return Jwts.builder()
                .setExpiration(expire_time)
                .claim("id", id)
                .claim("passwordMd5", passwordMd5) // 私有部分，实际上真正需要封装的信息（id和name）
                .signWith(SignatureAlgorithm.HS256, tokenSignKey) // 签名部分
                .compact();
    }
    //从token中获取数据
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey("xizhihinaForeverSukiHIMEHINAandZhandoubagejiANDtianyinbifang")
                .parseClaimsJws(token)
                .getBody();
    }
    ////数据库到User的映射
    //private RowMapper<User> GetUserMapper(){
    //    return (ResultSet rs, int rownum)->{
    //        User user=new User();
    //        user.setId(rs.getInt("id"));
    //        user.setNickName(rs.getString("nick_name"));
    //        user.setPasswordMd5(rs.getString("password_Md5"));
    //        user.setToken(rs.getString("token"));
    //        user.setLevel(rs.getInt("level"));
    //        return user;
    //    };
    //}
}
