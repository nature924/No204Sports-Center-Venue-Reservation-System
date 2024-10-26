package com.tyg.tygyy.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyg.tygyy.entity.User;
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
public class UserController {


    @Autowired
    UserService service;

    //删除用户
    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        service.delete(id);
        message = "yes";
        return message;
    }

    //新增用户页面
    @RequestMapping("/toAddUser")
    public String toAddUsers(){
        return "user/add";
    }

    //注册用户页面
    @RequestMapping("/register")
    public String register(){
        return "user/register";
    }

    //新增用户
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(User data, Model model, HttpSession httpSession){
        String message = "no";
        User user = service.selectBykey(data);
        if(user == null){
            data.setRegiste_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            service.add(data);
            message = "yes";
        }else{
            message = "is";
        }
        return message;
    }

    //修改用户
    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(User data, Model model, HttpSession httpSession){
        String message = "no";
        service.update(data);
        message = "yes";
        return message;
    }


    //修改页面
    @RequestMapping("/toUpdateUser")
    public String toUpdateUser(Integer id, Model model, HttpServletRequest request){
        User data =service.findById(id);
        request.setAttribute("data",data);
        return "user/update";
    }

    //查询用户
    @RequestMapping("/UserList")
    public String UserList(Model model, User data,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = service.findList(data);
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","UserList");
        model.addAttribute("data",data);
        return "user/list";

    }

}
