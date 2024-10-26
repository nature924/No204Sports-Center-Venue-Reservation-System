package com.tyg.tygyy.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyg.tygyy.entity.Category;
import com.tyg.tygyy.entity.Spot;
import com.tyg.tygyy.entity.Statics;
import com.tyg.tygyy.service.CategoryService;
import com.tyg.tygyy.service.SpotService;
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
public class SpotController {


    @Autowired
    SpotService service;

    @Autowired
    CategoryService categoryService;

    //删除场地
    @RequestMapping("/deleteSpot")
    @ResponseBody
    public String deleteSpot(Integer id){
        String message = "no";
        service.delete(id);
        message = "yes";
        return message;
    }

    //新增场地页面
    @RequestMapping("/toAddSpot")
    public String toAddSpots(HttpServletRequest request){
        List<Category> categoryList = categoryService.findList(new Category());
        request.setAttribute("categoryList",categoryList);
        return "spot/add";
    }

    //新增场地
    @RequestMapping("/addSpot")
    @ResponseBody
    public String addSpot(Spot data){
        String message = "no";
        Spot spot = service.selectBykey(data);
        if(spot == null){
            service.add(data);
            message = "yes";
        }else{
            message = "is";
        }
        return message;
    }

    //修改场地
    @RequestMapping("/updateSpot")
    @ResponseBody
    public String updateSpot(Spot data){
        String message = "no";
        service.update(data);
        message = "yes";
        return message;
    }


    //启用禁用场地
    @RequestMapping("/updateStateSpot")
    @ResponseBody
    public String updateStateSpot(Spot data){
        String message = "no";
        service.updateStateSpot(data);
        message = "yes";
        return message;
    }

    //统计场地根据分类
    @RequestMapping("/selectStaticsSpot")
    @ResponseBody
    public List<Statics> selectStaticsSpot(){
        return service.selectStaticsSpot();
    }

    //统计场地根据预约次数
    @RequestMapping("/selectStaticsSpotRecord")
    @ResponseBody
    public List<Statics> selectStaticsSpotRecord(){
        return service.selectStaticsSpotRecord();
    }

    //修改页面
    @RequestMapping("/toUpdateSpot")
    public String toUpdateSpot(Integer id, HttpServletRequest request){
        Spot data =service.findById(id);
        request.setAttribute("data",data);
        List<Category> categoryList = categoryService.findList(new Category());
        request.setAttribute("categoryList",categoryList);
        return "spot/update";
    }

    //查询场地
    @RequestMapping("/SpotList")
    public String SpotList(Model model, Spot data,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                  HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);
        List<Spot> list = service.findList(data);
        for(Spot spot : list){
            spot.setCategory(categoryService.findById(spot.getCid()));
        }
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","SpotList");
        model.addAttribute("data",data);
        return "spot/list";
    }

}
