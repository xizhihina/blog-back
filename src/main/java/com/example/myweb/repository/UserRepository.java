package com.example.myweb.repository;

import com.example.myweb.pojo.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
