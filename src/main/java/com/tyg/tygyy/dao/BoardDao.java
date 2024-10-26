package com.tyg.tygyy.dao;


import com.tyg.tygyy.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardDao {

    void delete(@Param("id") Integer id);

    void add(@Param("data") Board data);

    void update(@Param("data") Board data);

    Board findById( @Param("id")Integer id);

    List<Board> findList( @Param("data") Board data);
}
