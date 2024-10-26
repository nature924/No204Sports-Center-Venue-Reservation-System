package com.tyg.tygyy.service.impl;


import com.tyg.tygyy.dao.CategoryDao;
import com.tyg.tygyy.entity.Category;
import com.tyg.tygyy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao dao;

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public void add(Category data) {
        dao.add(data);
    }

    @Override
    public void update(Category data) {
        dao.update(data);
    }

    @Override
    public Category findById(Integer id) {
        return  dao.findById(id);
    }

    @Override
    public List<Category> findList(Category data) {
        return  dao.findList(data);
    }

    @Override
    public Category selectBykey(Category data) {
        return dao.selectBykey(data);
    }
}
