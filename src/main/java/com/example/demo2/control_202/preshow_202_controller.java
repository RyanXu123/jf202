package com.example.demo2.control_202;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class preshow_202_controller {


    @Autowired
    private JdbcTemplate jdbc;
    @CrossOrigin
    @RequestMapping("/getData/202/preshow")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_pre1() {//一个时刻68个数据
        String sql = "select * from predata where Location='JF202' ORDER BY id DESC limit 0,13600";
        String sql1 = "select * from preshow where Location='JF202' ORDER BY id DESC limit 0,13600";
        List<Map<String, Object>> list = jdbc.queryForList(sql);
        Collections.reverse(list);//按时间翻转
        List<Map<String, Object>> list2 = jdbc.queryForList(sql1);
        Collections.reverse(list2);
//        Map<String,Object> ret=new HashMap<>();
        list.addAll(list2);
//        ret.put("realdata",list2);
//        ret.put("predata",list);
        return list;
    }


}
