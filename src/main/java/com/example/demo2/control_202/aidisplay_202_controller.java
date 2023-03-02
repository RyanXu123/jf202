package com.example.demo2.control_202;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class aidisplay_202_controller {

    @Autowired
    private JdbcTemplate jdbc;


    @CrossOrigin
    @RequestMapping("/getData/202/aidisplay")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<String> aidisplay(){
        String sql="select * from aidisplay";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        List<String> ret=new ArrayList<>();
        LinkedHashMap<String,Object> ai= new LinkedHashMap<>();

        for(Map<String,Object> c:list){
//            Object Contents=c.get("Contents");
            Object Details=c.get("Detail");
//            String params =(c.get("Params")).toString();
            ret.add(Details.toString());
        }

        return ret;
    }




}
