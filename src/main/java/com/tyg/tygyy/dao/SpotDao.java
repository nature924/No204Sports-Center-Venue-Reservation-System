package com.tyg.tygyy.dao;


import com.tyg.tygyy.entity.Spot;
import com.tyg.tygyy.entity.Statics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SpotDao {

    void delete(@Param("id") Integer id);

    void add(@Param("data") Spot data);

    void update(@Param("data") Spot data);

    Spot findById( @Param("id")Integer id);

    List<Spot> findList( @Param("data") Spot data);

    Spot selectBykey( @Param("data") Spot data);

    List<Statics> selectStaticsSpot();

    List<Statics> selectStaticsSpotRecord();

    void updateStateSpot(@Param("data") Spot data);
}
