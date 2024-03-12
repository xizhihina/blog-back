package com.blog.mapper;

import com.blog.pojo.MyInfo;


public interface MyInfoMapper {
    MyInfo select();

    boolean update(MyInfo myInfo);
}
