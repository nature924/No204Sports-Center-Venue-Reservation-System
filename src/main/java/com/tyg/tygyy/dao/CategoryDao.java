package com.tyg.tygyy.dao;


import com.tyg.tygyy.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryDao {

    void delete(@Param("id") Integer id);

    void add(@Param("data") Category data);

    void update(@Param("data") Category data);

    Category findById( @Param("id")Integer id);

    List<Category> findList( @Param("data") Category data);

    Category selectBykey( @Param("data") Category data);
}
