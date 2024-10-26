package com.tyg.tygyy.service.impl;


import com.tyg.tygyy.dao.RecordDao;
import com.tyg.tygyy.entity.Record;
import com.tyg.tygyy.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao boardDao;

    @Override
    public void delete(Integer id) {
        boardDao.delete(id);
    }

    @Override
    public void add(Record data) {
        boardDao.add(data);
    }

    @Override
    public void update(Record data) {
        boardDao.update(data);
    }

    @Override
    public Record findById(Integer id) {
        return  boardDao.findById(id);
    }

    @Override
    public List<Record> findList(Record data) {
        return  boardDao.findList(data);
    }

    @Override
    public void updateRecordState(Record record) {
        boardDao.updateRecordState(record);
    }
}
