package com.tyg.tygyy.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyg.tygyy.entity.Record;
import com.tyg.tygyy.entity.User;
import com.tyg.tygyy.service.RecordService;
import com.tyg.tygyy.service.SpotService;
import com.tyg.tygyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class RecordController {


    @Autowired
    RecordService recordService;

    @Autowired
    UserService userService;
    @Autowired
    SpotService spotService;


    //删除预约记录
    @RequestMapping("/deleteRecord")
    @ResponseBody
    public String deleteRecord(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        recordService.delete(id);
        message = "yes";
        return message;
    }


    //审核预约记录
    @RequestMapping("/updateRecordState")
    @ResponseBody
    public String updateRecordState(Record record, Model model, HttpSession httpSession){
        String message = "no";
        recordService.updateRecordState(record);
        message = "yes";
        return message;
    }

    //新增预约记录页面
    @RequestMapping("/toAddRecord")
    public String toAddRecords(Integer id,Model model){
        model.addAttribute("sid",id);
        return "record/add";
    }

    //新增预约记录
    @RequestMapping("/addRecord")
    @ResponseBody
    public String addRecord(Record data, Model model, HttpSession httpSession){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String message = "no";
        User user = (User)httpSession.getAttribute("user");
        data.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        if(user!=null){
            data.setUid(user.getId());
            data.setYybh(getRno());

            String start_time = data.getYy_date()+" "+data.getStart_time();
            String end_time = data.getYy_date()+" "+data.getEnd_time();

            Record re = new Record();
            re.setSid(data.getSid());
            boolean add = true;
            List<Record> list = recordService.findList(re);
            for(Record record:list){
                String start =  record.getYy_date()+" "+record.getStart_time();
                String end =  record.getYy_date()+" "+record.getEnd_time();
                try {
                    if(isEffectiveDate(sim.parse(start_time),sim.parse(start),sim.parse(end))){
                        add= false;
                        break;
                    }else if(isEffectiveDate(sim.parse(end_time),sim.parse(start),sim.parse(end))){
                        add= false;
                        break;
                    }else if(isEffectiveDate(sim.parse(start),sim.parse(end_time),sim.parse(start_time))){
                        add= false;
                        break;
                    }else if(isEffectiveDate(sim.parse(end),sim.parse(end_time),sim.parse(start_time))){
                        add= false;
                        break;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if(add){
                recordService.add(data);
                message = "yes";
            }else{
                message = "is";
            }
        }
        return message;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    public String getRno() {//生成订单号
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;

    }

    //修改预约记录
    @RequestMapping("/updateRecord")
    @ResponseBody
    public String updateRecord(Record data, Model model, HttpSession httpSession){
        String message = "no";
        recordService.update(data);
        message = "yes";
        return message;
    }


    //修改页面
    @RequestMapping("/toUpdateRecord")
    public String toUpdateRecord(Integer id, Model model, HttpServletRequest request){
        Record data =recordService.findById(id);
        request.setAttribute("data",data);
        return "record/update";
    }

    //查询预约记录
    @RequestMapping("/RecordList")
    public String RecordList(Model model, Record data,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                                 HttpSession httpSession, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);
        List<Record> list = recordService.findList(data);
        for(Record record :list){
            record.setUser(userService.findById(record.getUid()));
            record.setSpot(spotService.findById(record.getSid()));
        }
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","RecordList");
        model.addAttribute("data",data);
        return "record/list";

    }
    
}
