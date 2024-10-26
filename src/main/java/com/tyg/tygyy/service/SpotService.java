package com.tyg.tygyy.service;


import com.tyg.tygyy.entity.Spot;
import com.tyg.tygyy.entity.Statics;

import java.util.List;

public interface SpotService {
    void delete(Integer id);

    void add(Spot data);

    void update(Spot data);

    Spot findById(Integer id);


    List<Spot> findList(Spot data);

    Spot selectBykey(Spot data);

    List<Statics> selectStaticsSpot();

    List<Statics> selectStaticsSpotRecord();

    void updateStateSpot(Spot data);
}
