package com.tyg.tygyy.dao;


import com.tyg.tygyy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    void delete(@Param("id") Integer id);

    void add(@Param("data") User data);

    void update(@Param("data") User data);

    User findById( @Param("id")Integer id);

    List<User> findList( @Param("data") User data);

    User selectBykey( @Param("data") User data);
}
