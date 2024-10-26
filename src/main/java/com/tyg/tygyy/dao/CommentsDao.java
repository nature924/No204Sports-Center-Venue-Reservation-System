package com.tyg.tygyy.dao;


import com.tyg.tygyy.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentsDao {

    void delete(@Param("id") Integer id);

    void add(@Param("data") Comments data);

    void update(@Param("data") Comments data);

    Comments findById( @Param("id")Integer id);

    List<Comments> findList( @Param("data") Comments data);
}
