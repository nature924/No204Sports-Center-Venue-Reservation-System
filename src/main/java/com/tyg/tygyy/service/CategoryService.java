package com.tyg.tygyy.service;


import com.tyg.tygyy.entity.Category;

import java.util.List;

public interface CategoryService {
    void delete(Integer id);

    void add(Category data);

    void update(Category data);

    Category findById(Integer id);


    List<Category> findList(Category data);

    Category selectBykey(Category data);
}
