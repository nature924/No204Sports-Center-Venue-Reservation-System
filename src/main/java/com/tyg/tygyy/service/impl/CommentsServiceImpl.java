package com.tyg.tygyy.service.impl;


import com.tyg.tygyy.dao.CommentsDao;
import com.tyg.tygyy.entity.Comments;
import com.tyg.tygyy.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    CommentsDao boardDao;

    @Override
    public void delete(Integer id) {
        boardDao.delete(id);
    }

    @Override
    public void add(Comments data) {
        boardDao.add(data);
    }

    @Override
    public void update(Comments data) {
        boardDao.update(data);
    }

    @Override
    public Comments findById(Integer id) {
        return  boardDao.findById(id);
    }

    @Override
    public List<Comments> findList(Comments data) {
        return  boardDao.findList(data);
    }
}
