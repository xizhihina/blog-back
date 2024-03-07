package com.example.myweb.pojo;

import com.example.myweb.param.UserParam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

@Data
public class User implements Persistable<Integer> {
    /**
     * 用户ID
     */
    @Id
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

    @Transient
    @JsonIgnore
    private boolean isInsert;

    @Override
    @JsonIgnore
    public boolean isNew() {
        return isInsert;
    }
}
