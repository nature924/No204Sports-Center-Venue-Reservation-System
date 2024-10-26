package com.tyg.tygyy.service.impl;


import com.tyg.tygyy.dao.UserDao;
import com.tyg.tygyy.entity.User;
import com.tyg.tygyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public void add(User data) {
        dao.add(data);
    }

    @Override
    public void update(User data) {
        dao.update(data);
    }

    @Override
    public User findById(Integer id) {
        return  dao.findById(id);
    }

    @Override
    public List<User> findList(User data) {
        return  dao.findList(data);
    }

    @Override
    public User selectBykey(User data) {
        return dao.selectBykey(data);
    }
}
