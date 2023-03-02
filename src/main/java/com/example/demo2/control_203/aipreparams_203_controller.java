package com.example.demo2.control_203;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class aipreparams_203_controller {
    @Autowired
    private JdbcTemplate jdbc;


    @CrossOrigin
    @RequestMapping("/getData/203/aipreparams")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_ai(){
        String sql="select * from aipreparams203";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        List <Map<String,Object>> list1=new ArrayList<>();
        List <Map<String,Object>> list2=new ArrayList<>();
        for (Map<String,Object> c :list){
            String content=c.get("Content").toString();
            if(content.equals("机房IT总功率") |content.equals("机房空调总功率") |content.equals("机房PUE") ){

            }else{
                list2.add(c);
            }
        }
        return list2;
    }


    @CrossOrigin
    @RequestMapping("/getData/203/jfparams")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_ai2(){
        String sql="select * from aipreparams203" +
                "";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        List <Map<String,Object>> list1=new ArrayList<>();
        List <Map<String,Object>> list2=new ArrayList<>();
        for (Map<String,Object> c :list){
            String content=c.get("Content").toString();
            if(content.equals("机房IT总功率") |content.equals("机房空调总功率") |content.equals("机房PUE") ){
                list1.add(c);
            }else{

            }
        }
        return list1;
    }
}
