package com.tyg.tygyy.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyg.tygyy.entity.Comments;
import com.tyg.tygyy.entity.User;
import com.tyg.tygyy.service.CommentsService;
import com.tyg.tygyy.service.UserService;
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
public class CommentsController {


    @Autowired
    CommentsService commentsService;

    @Autowired
    UserService userService;

    //删除评论
    @RequestMapping("/deleteComments")
    @ResponseBody
    public String deleteComments(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        commentsService.delete(id);
        message = "yes";
        return message;
    }

    //新增评论页面
    @RequestMapping("/toAddComments")
    public String toAddCommentss(){
        return "comments/add";
    }

    //新增评论
    @RequestMapping("/addComments")
    @ResponseBody
    public String addComments(Comments data, Model model, HttpSession httpSession){
        String message = "no";
        User user = (User)httpSession.getAttribute("user");
        data.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        if(user!=null){
            data.setUid(user.getId());
            commentsService.add(data);
            message = "yes";
        }
        return message;
    }

    //修改评论
    @RequestMapping("/updateComments")
    @ResponseBody
    public String updateComments(Comments data, Model model, HttpSession httpSession){
        String message = "no";
        commentsService.update(data);
        message = "yes";
        return message;
    }


    //修改页面
    @RequestMapping("/toUpdateComments")
    public String toUpdateComments(Integer id, Model model, HttpServletRequest request){
        Comments data =commentsService.findById(id);
        request.setAttribute("data",data);
        return "comments/update";
    }

    //查询评论
    @RequestMapping("/CommentsList")
    public String CommentsList(Model model, Comments data,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);
        List<Comments> list = commentsService.findList(data);
        for(Comments comments : list){
            comments.setUser(userService.findById(comments.getUid()));
        }
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","CommentsList");
        model.addAttribute("data",data);
        return "comments/list";

    }
    
}
