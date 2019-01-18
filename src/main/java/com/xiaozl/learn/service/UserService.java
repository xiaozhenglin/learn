package com.xiaozl.learn.service;

import java.util.List;

import com.xiaozl.learn.entity1.User;

/**
 * Created by songsf on 2017/4/7.
 */
public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User create(User user);
    User edit(User user);
    void deleteById(Long id);
}
