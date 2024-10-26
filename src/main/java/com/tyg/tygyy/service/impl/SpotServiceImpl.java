package com.tyg.tygyy.service.impl;


import com.tyg.tygyy.dao.SpotDao;
import com.tyg.tygyy.entity.Spot;
import com.tyg.tygyy.entity.Statics;
import com.tyg.tygyy.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotServiceImpl implements SpotService {

    @Autowired
    SpotDao dao;

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public void add(Spot data) {
        dao.add(data);
    }

    @Override
    public void update(Spot data) {
        dao.update(data);
    }

    @Override
    public Spot findById(Integer id) {
        return  dao.findById(id);
    }

    @Override
    public List<Spot> findList(Spot data) {
        return  dao.findList(data);
    }

    @Override
    public Spot selectBykey(Spot data) {
        return dao.selectBykey(data);
    }

    @Override
    public List<Statics> selectStaticsSpot() {
        return dao.selectStaticsSpot();
    }

    @Override
    public List<Statics> selectStaticsSpotRecord() {
        return dao.selectStaticsSpotRecord();
    }

    @Override
    public void updateStateSpot(Spot data) {
        dao.updateStateSpot(data);
    }
}
