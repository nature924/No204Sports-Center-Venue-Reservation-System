package com.tyg.tygyy.service;


import com.github.pagehelper.PageInfo;
import com.tyg.tygyy.entity.Board;

import java.util.List;

public interface BoardService {
    void delete(Integer id);

    void add(Board data);

    void update(Board data);

    Board findById(Integer id);


    List<Board> findList(Board data);
}
