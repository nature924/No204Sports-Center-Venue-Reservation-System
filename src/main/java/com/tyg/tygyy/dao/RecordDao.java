package com.tyg.tygyy.dao;


import com.tyg.tygyy.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordDao {

    void delete(@Param("id") Integer id);

    void add(@Param("data") Record data);

    void update(@Param("data") Record data);

    Record findById( @Param("id")Integer id);

    List<Record> findList( @Param("data") Record data);

    void updateRecordState(@Param("record") Record record);
}
