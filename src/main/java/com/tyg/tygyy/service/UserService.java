package com.tyg.tygyy.service;


import com.tyg.tygyy.entity.User;

import java.util.List;

public interface UserService {
    void delete(Integer id);

    void add(User data);

    void update(User data);

    User findById(Integer id);


    List<User> findList(User data);

    User selectBykey(User data);
}
