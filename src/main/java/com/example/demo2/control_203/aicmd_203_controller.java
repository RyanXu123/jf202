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
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class aicmd_203_controller {
//
//    @Autowired
//    private JdbcTemplate jdbc;
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/aicmd")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata203_aicmd(){
//        String sql="select * from aicmd";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//}
