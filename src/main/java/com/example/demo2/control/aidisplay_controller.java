package com.example.demo2.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class aidisplay_controller {

    @Autowired
    private JdbcTemplate jdbc;


    @CrossOrigin
    @RequestMapping("/getData/202/aidisplay")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_ai(){
        String sql="select * from aidisplay";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        List <Map<String,Object>> list_all=new ArrayList<>();
        Map<String,Object> ai= new HashMap<>();
//        List Content= new ArrayList<>();
//        List Detail = new ArrayList<>();

        Map<String,Object> Content= new HashMap<>();
        Map<String,Object> Detail= new HashMap<>();
        for(Map<String,Object> c:list){
            Object Contents=c.get("Contents");
            Object Details=c.get("Detail");
            String params =(c.get("Params")).toString();
            Content.put(params,Contents);
            Detail.put(params,Details);
        }
        ai.put("AI状态参数",Content);
        ai.put("AI状态详情",Detail);
        list_all.add(ai);
        return list_all;
    }

}
