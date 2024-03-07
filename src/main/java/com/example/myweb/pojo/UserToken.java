package com.example.myweb.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class UserToken {
    /**
     * id
     */
    @Id
    private int id;
    /**
     * token
     */
    private String token;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 过期时间
     */
    private Date expireTime;
}
