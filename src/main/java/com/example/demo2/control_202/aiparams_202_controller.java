package com.example.demo2.control_202;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class aiparams_202_controller {
    @Autowired
    private JdbcTemplate jdbc;


    @CrossOrigin
    @RequestMapping("/getData/202/aiparams")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_ai(){
        String sql="select * from aiparams limit 0,4";
        List <Map<String,Object>> list=jdbc.queryForList(sql);

        return list;
    }
    @PostMapping("/getData/202/aiparams")
    public String getData(@RequestBody Map<String,String> data){
        String name=data.get("");
        return "";
    }

//    @CrossOrigin
//    @RequestMapping("/202/AI_data")
//    @ResponseBody
////    @Scheduled(fixedRate = 30000)
//    public Map<String,Object> params(){
//        Map<String,Object> m=new HashMap<>();
//        m.put("热点检查阈值","26.8℃");
////        m.put("AI预控控制范围","2℃");
//        m.put("AI群控控制范围","1℃");
//        m.put("AI群控控制周期","30min");
//        m.put("AI控制状态","节能");
////        m.put("AI控制状态","安全");
//        return m;
//    }
}
