package com.example.demo2.control_203;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@Controller
public class precmd_203_controller {

    @Autowired
    private JdbcTemplate jdbc;
    @CrossOrigin
    @RequestMapping("/getData/203/precmd")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata203_pre(){ //按服务器和空调变化状态给数据

        String sql="select * from precmd203 where Location='JF203'";
        String sql2="select * from predata203 where Location='JF203'and PointName='冷通道最大温度' limit 0,6";
        List <Map<String,Object>> list0= new ArrayList<>();
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        List <Map<String,Object>> list2=jdbc.queryForList(sql2);
//        list.addAll(list2);
        TreeMap<String,Integer> kt= new TreeMap<>(); //字符串为null
        TreeMap<String,Object> server= new TreeMap<>();
        TreeMap<String,Object> server_all= new TreeMap<>();
        TreeMap<String,Object> kt_all= new TreeMap<>();
        for(Map<String,Object>c :list){
            String name=c.get("HelperKt").toString().substring(2);
            Integer value=Integer.parseInt(c.get("ChangedValue").toString());
            kt.put(name,value);
        }
        for(Map<String,Object>c :list2){
            String name=c.get("Equipment").toString().substring(3);
            Object value=c.get("Value0");
            server.put(name,value);
        }

        kt_all.put("kt",kt);
        server_all.put("server",server);
        list0.add(kt_all);
        list0.add(server_all);
        return list0;
    }

}
