package com.tyg.tygyy.service;

import com.tyg.tygyy.entity.Admin;
import com.tyg.tygyy.entity.User;


public interface LoginService {
    Admin selectAdmin(String username, String password);

    User selectUser(String username, String password);


    void updateAdmin(Admin admin);
}
