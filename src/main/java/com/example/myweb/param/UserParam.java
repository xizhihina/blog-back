package com.example.myweb.param;

import lombok.Data;

@Data
public class UserParam {
    /**
     * 用户ID
     */
    private int id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * bilibiliID
     */
    private String  bid;
    /**
     * QQID
     */
    private String qid;
    /**
     * 等级
     */
    private int level;
    public UserParam(int id,String nickName,int level,String bid,String qid){
        this.id=id;
        this.nickName=nickName;
        this.level=level;
        this.bid=bid;
        this.qid=qid;
    }
}
