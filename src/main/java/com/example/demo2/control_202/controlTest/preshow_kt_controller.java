//package com.example.demo2.control_202;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class preshow_kt_controller {
//
//
//    @Autowired
//    private JdbcTemplate jdbc;
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/supplyair")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata202_preshow1(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and PointName='送风温度'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/supplyair")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata202_preshow2(){
//        String sql="select * from preshow4 where Location='JF202' and Type='预测' and PointName='送风温度'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/returnair")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata202_preshow3(){
//        String sql="select * from preshow4 where Location='JF202' and Type='实时'and PointName='回风温度'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/returnair")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata202_preshow4(){
//        String sql="select * from preshow4 where Location='JF202' and Type='预测'and PointName='回风温度'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//
//
//
//}
