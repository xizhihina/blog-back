package com.blog.mapper;

import com.blog.pojo.User;

import java.util.List;


public interface UserMapper {
    User select(int id);
    boolean delete(int id);

    boolean update(User user);

    User selectByToken(String token);

    List<User> selectAll();

    boolean insert(User user);
}
