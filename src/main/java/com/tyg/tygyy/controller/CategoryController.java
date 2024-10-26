package com.tyg.tygyy.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyg.tygyy.entity.Category;
import com.tyg.tygyy.service.CategoryService;
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
public class CategoryController {


    @Autowired
    CategoryService service;

    //删除场地类别
    @RequestMapping("/deleteCategory")
    @ResponseBody
    public String deleteCategory(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        service.delete(id);
        message = "yes";
        return message;
    }

    //新增场地类别页面
    @RequestMapping("/toAddCategory")
    public String toAddCategory(){
        return "category/add";
    }

    //新增场地类别
    @RequestMapping("/addCategory")
    @ResponseBody
    public String addCategory(Category data, Model model, HttpSession httpSession){
        String message = "no";
        Category category = service.selectBykey(data);
        if(category == null){
            service.add(data);
            message = "yes";
        }else{
            message = "is";
        }
        return message;
    }

    //修改场地类别
    @RequestMapping("/updateCategory")
    @ResponseBody
    public String updateCategory(Category data, Model model, HttpSession httpSession){
        String message = "no";
        service.update(data);
        message = "yes";
        return message;
    }


    //修改页面
    @RequestMapping("/toUpdateCategory")
    public String toUpdateCategory(Integer id, Model model, HttpServletRequest request){
        Category data =service.findById(id);
        request.setAttribute("data",data);
        return "category/update";
    }

    //查询场地类别
    @RequestMapping("/CategoryList")
    public String CategoryList(Model model, Category data,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);
        List<Category> list = service.findList(data);
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","CategoryList");
        model.addAttribute("data",data);
        return "category/list";

    }
    
}
