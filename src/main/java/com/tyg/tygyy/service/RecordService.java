package com.tyg.tygyy.service;


import com.tyg.tygyy.entity.Record;

import java.util.List;

public interface RecordService {
    void delete(Integer id);

    void add(Record data);

    void update(Record data);

    Record findById(Integer id);


    List<Record> findList(Record data);

    void updateRecordState(Record record);
}
