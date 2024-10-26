package com.tyg.tygyy.service;


import com.tyg.tygyy.entity.Comments;

import java.util.List;

public interface CommentsService {
    void delete(Integer id);

    void add(Comments data);

    void update(Comments data);

    Comments findById(Integer id);


    List<Comments> findList(Comments data);
}
