package com.tyg.tygyy.service.impl;

import com.tyg.tygyy.dao.LoginDao;
import com.tyg.tygyy.entity.Admin;
import com.tyg.tygyy.entity.User;
import com.tyg.tygyy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao dao;

    @Override
    public Admin selectAdmin(String username, String password) {
        return dao.selectAdmin(username,password);
    }

    @Override
    public User selectUser(String username, String password) {
        return dao.selectUser(username,password);
    }

    @Override
    public void updateAdmin(Admin admin) {
        dao.updateAdmin(admin);
    }
}
