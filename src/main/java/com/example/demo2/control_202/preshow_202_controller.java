package com.example.demo2.control_202;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class preshow_202_controller {


    @Autowired
    private JdbcTemplate jdbc;
    @CrossOrigin
    @RequestMapping("/getData/202/preshow")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public Map<String,Object> getdata202_pre1() {//一个时刻68个数据

        String sql_sf="select * from predata where Location='JF202' and PointName='回风温度' ORDER BY id DESC limit 200";
        String sql_hf="select * from predata where Location='JF202' and PointName='送风温度'  ORDER BY id DESC limit 0,200";
        String sql_coldmax="select * from predata where Location='JF202' and PointName='冷通道最大温度'  ORDER BY id DESC limit 0,140";
        String sql_hotavg="select * from predata where Location='JF202' and PointName='热通道平均温度'  ORDER BY id DESC limit 0,140";
        String sql_power="select * from predata where Location='JF202' and PointName='服务器功率'  ORDER BY id DESC limit 0,70";

//        List<Map<String, Object>> list_sf = jdbc.queryForList(sql_sf);
//        List<Map<String, Object>> list_hf = jdbc.queryForList(sql_hf);
//        List<Map<String, Object>> list_coldmax = jdbc.queryForList(sql_coldmax);
//        List<Map<String, Object>> list_hotavg = jdbc.queryForList(sql_hotavg);
//        List<Map<String, Object>> list_power = jdbc.queryForList(sql_power);
//        List<Map<String, Object>> time_line = jdbc.queryForList(sql_hotavg);

        Map<Integer,List<String>>sf_map= new HashMap<>();
        Map<Integer,List<String>>hf_map= new HashMap<>();
        Map<Integer,List<String>>coldmax_map= new HashMap<>();
        Map<Integer,List<String>>hotavg_map= new HashMap<>();
        Map<Integer,List<String>>power_map= new HashMap<>();
        List <String>timeline_arr= new ArrayList<>();


//        List<List<String>>sf_arr = new ArrayList<>();
//
//        List<String>sf_arr1 = new ArrayList<>();
//        List<String>sf_arr2 = new ArrayList<>();
//        List<String>sf_arr3 = new ArrayList<>();
//        List<String>sf_arr4 = new ArrayList<>();
//        List<String>sf_arr5 = new ArrayList<>();
//        List<String>sf_arr6 = new ArrayList<>();
//        List<String>sf_arr7 = new ArrayList<>();
//        List<String>sf_arr8 = new ArrayList<>();
//        List<String>sf_arr9 = new ArrayList<>();
//        List<String>sf_arr10 = new ArrayList<>();
//        List<String>sf_arr11 = new ArrayList<>();
//        List<String>sf_arr12 = new ArrayList<>();
//        List<String>sf_arr13 = new ArrayList<>();
//        List<String>sf_arr14 = new ArrayList<>();
//        List<String>sf_arr15 = new ArrayList<>();
//        List<String>sf_arr16 = new ArrayList<>();
//        List<String>sf_arr17 = new ArrayList<>();
//        List<String>sf_arr18 = new ArrayList<>();
//        List<String>sf_arr19 = new ArrayList<>();
//        List<String>sf_arr20 = new ArrayList<>();


//
//        List<String>hf_arr= new ArrayList<>();
//        List<String>coldmax_arr= new ArrayList<>();
//        List<String>hotavg_arr= new ArrayList<>();
//        List<String>power_arr= new ArrayList<>();

        List<List<String>>sf_arr_all = new ArrayList<>();
        List<List<String>>hf_arr_all= new ArrayList<>();
        List<List<String>>coldmax_arr_all= new ArrayList<>();
        List<List<String>>hotavg_arr_all= new ArrayList<>();
        List<List<String>>power_arr_all= new ArrayList<>();

//        String[][] sf_arr = new String[20][];

        Integer cnt=1;
        for(;cnt<=20;cnt++){
            List<String>sf_arr = new ArrayList<>();
            sql_sf.replace("where","where Equipment=空调"+cnt+" and");
            List<Map<String, Object>> list_sf = jdbc.queryForList(sql_sf);
            for(Map<String, Object> c:list_sf){
                String Value0=c.get("Value0").toString();
                sf_arr.add(Value0);
            }
            Collections.reverse(sf_arr);
            sf_arr_all.add(sf_arr);
        }


        for(cnt=1;cnt<=20;cnt++){
            List<String> hf_arr = new ArrayList<>();
            sql_hf.replace("where","where Equipment=空调"+cnt+" and");
            List<Map<String, Object>> list_hf = jdbc.queryForList(sql_hf);
            for(Map<String, Object> c:list_hf){
                String Value0=c.get("Value0").toString();
                hf_arr.add(Value0);
            }
            Collections.reverse(hf_arr);
            hf_arr_all.add(hf_arr);
        }

        List<String> list_server=Arrays.asList("AB","CD","EF","GH","JK","LM","NP");
        for(cnt=0;cnt<7;cnt++){
            List<String> coldmax_arr = new ArrayList<>();
            sql_coldmax.replace("where","where Equipment=服务器"+list_server.get(cnt)+" and");
            List<Map<String, Object>> list_coldmax = jdbc.queryForList(sql_coldmax);
            for(Map<String, Object> c:list_coldmax){
                String Value0=c.get("Value0").toString();
                coldmax_arr.add(Value0);
            }
            Collections.reverse(coldmax_arr);
            coldmax_arr_all.add(coldmax_arr);
        }


        for(cnt=0;cnt<7;cnt++){
            List<String> hotavg_arr = new ArrayList<>();
            sql_hotavg.replace("where","where Equipment=服务器"+list_server.get(cnt)+" and");
            List<Map<String, Object>> list_hotavg = jdbc.queryForList(sql_hotavg);
            for(Map<String, Object> c:list_hotavg){
                String Value0=c.get("Value0").toString();
                hotavg_arr.add(Value0);
            }
            Collections.reverse(hotavg_arr);
            hotavg_arr_all.add(hotavg_arr);
        }

        for(cnt=0;cnt<7;cnt++){
            List<String> power_arr = new ArrayList<>();
            sql_power.replace("where","where Equipment=服务器"+list_server.get(cnt)+" and");
            List<Map<String, Object>> list_power = jdbc.queryForList(sql_power);
            for(Map<String, Object> c:list_power){
                String Value0=c.get("Value0").toString();
                String time0=c.get("time").toString();
                power_arr.add(Value0);
                timeline_arr.add(time0);
                if(cnt==0){//记录时间线
                    Collections.reverse(timeline_arr);
                }
            }
            Collections.reverse(power_arr);
            power_arr_all.add(power_arr);
        }


        Map<String,Object> server=new HashMap<>();
        Map<String,Object> kt= new HashMap<>();
        server.put("power",power_arr_all);
        server.put("coldmax",coldmax_arr_all);
        server.put("hotavg",hotavg_arr_all);
        kt.put("hf",hf_arr_all);
        kt.put("sf",sf_arr_all);



        String sql_sf_now="select * from preshow where Location='JF202' and PointName='回风温度' ORDER BY id DESC limit 200";
        String sql_hf_now="select * from preshow where Location='JF202' and PointName='送风温度'  ORDER BY id DESC limit 0,200";
        String sql_coldmax_now="select * from preshow where Location='JF202' and PointName='冷通道最大温度'  ORDER BY id DESC limit 0,140";
        String sql_hotavg_now="select * from preshow where Location='JF202'and  PointName='热通道平均温度'  ORDER BY id DESC limit 0,140";
        String sql_power_now="select * from preshow where Location='JF202' and PointName='服务器功率'  ORDER BY id DESC limit 0,70";


        List<List<String>>sf_arr_all_now = new ArrayList<>();
        List<List<String>>hf_arr_all_now= new ArrayList<>();
        List<List<String>>coldmax_arr_all_now= new ArrayList<>();
        List<List<String>>hotavg_arr_all_now= new ArrayList<>();
        List<List<String>>power_arr_all_now= new ArrayList<>();

//        String[][] sf_arr = new String[20][];

        for(cnt=1;cnt<=20;cnt++){
            List<String>sf_arr = new ArrayList<>();
            sql_sf_now.replace("where","where Equipment=空调"+cnt+" and");
            List<Map<String, Object>> list_sf_now = jdbc.queryForList(sql_sf);
            for(Map<String, Object> c:list_sf_now){
                String Value0=c.get("Value0").toString();
                sf_arr.add(Value0);
            }
            Collections.reverse(sf_arr);
            sf_arr_all_now.add(sf_arr);
        }


        for(cnt=1;cnt<=20;cnt++){
            List<String> hf_arr = new ArrayList<>();
            sql_hf_now.replace("where","where Equipment=空调"+cnt+" and");
            List<Map<String, Object>> list_hf_now = jdbc.queryForList(sql_hf);
            for(Map<String, Object> c:list_hf_now){
                String Value0=c.get("Value0").toString();
                hf_arr.add(Value0);
            }
            Collections.reverse(hf_arr);
            hf_arr_all_now.add(hf_arr);
        }

        for(cnt=0;cnt<7;cnt++){
            List<String> coldmax_arr = new ArrayList<>();
            sql_coldmax_now.replace("where","where Equipment=服务器"+list_server.get(cnt)+" and");
            List<Map<String, Object>> list_coldmax_now = jdbc.queryForList(sql_coldmax);
            for(Map<String, Object> c:list_coldmax_now){
                String Value0=c.get("Value0").toString();
                coldmax_arr.add(Value0);
            }
            Collections.reverse(coldmax_arr);
            coldmax_arr_all_now.add(coldmax_arr);
        }


        for(cnt=0;cnt<7;cnt++){
            List<String> hotavg_arr = new ArrayList<>();
            sql_hotavg_now.replace("where","where Equipment=服务器"+list_server.get(cnt)+" and");
            List<Map<String, Object>> list_hotavg_now = jdbc.queryForList(sql_hotavg);
            for(Map<String, Object> c:list_hotavg_now){
                String Value0=c.get("Value0").toString();
                hotavg_arr.add(Value0);
            }
            Collections.reverse(hotavg_arr);
            hotavg_arr_all_now.add(hotavg_arr);
        }

        for(cnt=0;cnt<7;cnt++){
            List<String> power_arr = new ArrayList<>();
            sql_power_now.replace("where","where Equipment=服务器"+list_server.get(cnt)+" and");
            List<Map<String, Object>> list_power_now = jdbc.queryForList(sql_power);
            for(Map<String, Object> c:list_power_now){
                String Value0=c.get("Value0").toString();
                String time0=c.get("time").toString();
                power_arr.add(Value0);
                timeline_arr.add(time0);
                if(cnt==0){//记录时间线
                    Collections.reverse(timeline_arr);
                }
            }
            Collections.reverse(power_arr);
            power_arr_all_now.add(power_arr);
        }


        Map<String,Object> server_now=new HashMap<>();
        Map<String,Object> kt_now= new HashMap<>();
        server_now.put("power",power_arr_all_now);
        server_now.put("coldmax",coldmax_arr_all_now);
        server_now.put("hotavg",hotavg_arr_all_now);
        kt_now.put("hf",hf_arr_all_now);
        kt_now.put("sf",sf_arr_all_now);

        Map<String,Object> pre= new HashMap<>();
        Map<String,Object> real= new HashMap<>();
        Map<String,Object> ret= new HashMap<>();
        pre.put("server",server);
        pre.put("kt",kt);
        real.put("server",server_now);
        real.put("kt",kt_now);
        ret.put("pre",pre);
        ret.put("now",real);
        ret.put("timeline",timeline_arr);

        return ret;
    }
}
