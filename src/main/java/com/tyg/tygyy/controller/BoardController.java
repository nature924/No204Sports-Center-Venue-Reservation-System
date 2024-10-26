package com.tyg.tygyy.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyg.tygyy.entity.Board;
import com.tyg.tygyy.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BoardController {


    @Autowired
    BoardService boardService;

    //删除公告
    @RequestMapping("/deleteBoard")
    @ResponseBody
    public String deleteBoard(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        boardService.delete(id);
        message = "yes";
        return message;
    }

    //新增公告页面
    @RequestMapping("/toAddBoard")
    public String toAddBoards(){
        return "board/add";
    }

    //新增公告
    @RequestMapping("/addBoard")
    @ResponseBody
    public String addBoard(Board data, Model model, HttpSession httpSession){
        String message = "no";
        data.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        boardService.add(data);
        message = "yes";
        return message;
    }

    //修改公告
    @RequestMapping("/updateBoard")
    @ResponseBody
    public String updateBoard(Board data, Model model, HttpSession httpSession){
        String message = "no";
        boardService.update(data);
        message = "yes";
        return message;
    }


    //修改页面
    @RequestMapping("/toUpdateBoard")
    public String toUpdateBoard(Integer id, Model model, HttpServletRequest request){
        Board data =boardService.findById(id);
        request.setAttribute("data",data);
        return "board/update";
    }

    //查询公告
    @RequestMapping("/BoardList")
    public String BoardList(Model model, Board data,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);
        List<Board> list = boardService.findList(data);
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","BoardList");
        model.addAttribute("data",data);
        return "board/list";

    }
    
}
