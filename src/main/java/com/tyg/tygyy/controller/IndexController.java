package com.tyg.tygyy.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyg.tygyy.entity.*;
import com.tyg.tygyy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    SpotService spotService;

    @Autowired
    BoardService boardService;

    @Autowired
    CommentsService commentsService;

    @Autowired
    RecordService recordService;

    //场地页面
    @RequestMapping("/toIndex")
    public String toIndex(Model model, Spot data,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "6") Integer pageSize,
                                 HttpServletRequest request){
        data.setStatus("0");
        PageHelper.startPage(pageNum,pageSize);
        List<Spot> list = spotService.findList(data);
        for(Spot spot : list){
            spot.setCategory(categoryService.findById(spot.getCid()));
        }
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","toIndex");
        model.addAttribute("data",data);
        model.addAttribute("categoryList",categoryService.findList(new Category()));
        return "index/index";

    }


    //场地详情
    @RequestMapping("/toSpotDetail")
    public String toSpotDetail(Model model, Integer  id,HttpServletRequest request){
        Spot data = spotService.findById(id);
        model.addAttribute("data",data);
        model.addAttribute("url","toIndex");
        return "index/spot_detail";

    }


    //公告页面
    @RequestMapping("/toBoard")
    public String toBoard(Model model, Board data,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "6") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Board> list = boardService.findList(data);
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","toBoard");
        model.addAttribute("data",data);
        return "index/board";

    }

    //公告详情
    @RequestMapping("/toBoardDetail")
    public String toBoardDetail(Model model, Integer  id,HttpServletRequest request){
        Board data = boardService.findById(id);
        model.addAttribute("data",data);
        model.addAttribute("url","toBoard");
        Comments comments = new Comments();
        comments.setBid(id);
        List<Comments> commentsList = commentsService.findList(comments);
        for(Comments com:commentsList){
            com.setUser(userService.findById(com.getUid()));
        }
        model.addAttribute("commentsList",commentsList);
        return "index/board_detail";

    }


    //我的评论
    @RequestMapping("/toMyComments")
    public String toMyComments(Model model, Comments data,
                               @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false,defaultValue = "6") Integer pageSize,
                               HttpSession httpSession){
        User user = (User)httpSession.getAttribute("user");
        data.setUid(user.getId());
        PageHelper.startPage(pageNum,pageSize);
        List<Comments> list = commentsService.findList(data);
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","toMyComments");
        model.addAttribute("data",data);
        return "index/my_comments";

    }


    //个人信息
    @RequestMapping("/toMyInfo")
    public String toBoardDetail(Model model,  HttpSession httpSession){
        User user = (User)httpSession.getAttribute("user");
        if(user !=null){
            model.addAttribute("data",userService.findById(user.getId()));
        }
        return "index/my_info";
    }

    //我的预约
    @RequestMapping("/toMyRecord")
    public String toMyRecord(Model model, Record data,
                               @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false,defaultValue = "6") Integer pageSize,
                               HttpSession httpSession){
        User user = (User)httpSession.getAttribute("user");
        data.setUid(user.getId());
        PageHelper.startPage(pageNum,pageSize);
        List<Record> list = recordService.findList(data);
        for(Record record :list){
            record.setUser(userService.findById(record.getUid()));
            record.setSpot(spotService.findById(record.getSid()));
        }
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","toMyRecord");
        model.addAttribute("data",data);
        return "index/my_record";

    }

}
