package com.blog.pojo;

import com.blog.param.UserParam;
import lombok.Data;

@Data
public class User{
    /**
     * 用户ID
     */
    private Integer id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * bilibiliID
     */
    private String bid;
    /**
     * QQID
     */
    private String qid;
    /**
     * md5密码
     */
    private String passwordMd5;
    /**
     * token
     */
    private String token;
    /**
     * 等级
     */
    private int level;

    public User(int id, String passwordMd5) {
        this.id=id;
        this.passwordMd5=passwordMd5;
        this.nickName="新手工作人员";
        this.level=0;
    }

    public User() {

    }

    public UserParam GetUserWithoutPasswordToken() {
        return new UserParam(id,nickName,level,bid,qid);
    }
}
