//package com.example.demo2.control_203;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.*;
//
//@Controller
//public class aidisplay_203_controller {
//
//    @Autowired
//    private JdbcTemplate jdbc;
//
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/aidisplay")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata203_ai(){
//        String sql="select * from aidisplay";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        List <Map<String,Object>> list_all=new ArrayList<>();
//        Map<String,Object> ai= new HashMap<>();
////        List Content= new ArrayList<>();
////        List Detail = new ArrayList<>();
//
//        Map<String,Object> Content= new LinkedHashMap<>();//按读入顺序返回数据
//        Map<String,Object> Detail= new LinkedHashMap<>();
//
//        for(Map<String,Object> c:list){
//            Object Contents=c.get("Contents");
//            Object Details=c.get("Detail");
//            String params =(c.get("Params")).toString();
//            Content.put(params,Contents);
//            Detail.put(params,Details);
//        }
////        Collections.reverse(Content);
////        Collections.reverse(Content);
//        ai.put("AI状态参数",Content);
//        ai.put("AI状态详情",Detail);
//        list_all.add(ai);
////        Collections.reverse(list_all);
//        return list_all;
//    }
//
//}
