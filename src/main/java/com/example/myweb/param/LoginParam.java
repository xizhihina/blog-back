package com.example.myweb.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginParam implements Serializable {
    private int id;
    private String password;
}
