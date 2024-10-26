package com.tyg.tygyy.service.impl;


import com.github.pagehelper.PageInfo;
import com.tyg.tygyy.dao.BoardDao;
import com.tyg.tygyy.entity.Board;
import com.tyg.tygyy.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;

    @Override
    public void delete(Integer id) {
        boardDao.delete(id);
    }

    @Override
    public void add(Board data) {
        boardDao.add(data);
    }

    @Override
    public void update(Board data) {
        boardDao.update(data);
    }

    @Override
    public Board findById(Integer id) {
        return  boardDao.findById(id);
    }

    @Override
    public List<Board> findList(Board data) {
        return  boardDao.findList(data);
    }
}
