package com.blog.param;

import lombok.Data;

@Data
public class ChangePasswordParam {
    private int id;
    private String password_init;
    private String password_final;
}
