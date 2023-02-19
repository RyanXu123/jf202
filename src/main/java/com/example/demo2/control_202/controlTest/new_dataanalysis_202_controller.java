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
//import java.util.*;
//
//@Controller
//public class new_dataanalysis_202_controller {
//    @Autowired
//    private JdbcTemplate jdbc;
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/dataanalysisnew0")
//    @ResponseBody
////    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata202_data(){
//        List <Map<String,Object>> list_all= new ArrayList<Map<String,Object>>();
//        String  sql0_sf4=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='送风温度4' ORDER BY  unix_timestamp(time) ";
//        String  sql0_sf1=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='送风温度1' ORDER BY  unix_timestamp(time) ";
//        String  sql0_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='送风温度设定' ORDER BY  unix_timestamp(time) ";
//        String  sql0_hf1=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='回风温度1' ORDER BY  unix_timestamp(time) ";
//        String  sql0_hf2=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='回风温度2' ORDER BY  unix_timestamp(time) ";
//        String  sql0_hf3=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='回风温度3' ORDER BY  unix_timestamp(time) ";
//        String  sql0_hf4=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='回风温度4' ORDER BY  unix_timestamp(time) ";
//        String  sql0_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='回风温度设定' ORDER BY  unix_timestamp(time) ";
//        String  sql0_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time) ";
//        String  sql0_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time) ";
//        String  sql0_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='风机1转速' ORDER BY  unix_timestamp(time) ";
//        String  sql0_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='风机2转速' ORDER BY  unix_timestamp(time) ";
//        String  sql0_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调0' and PointName='空调功率' ORDER BY  unix_timestamp(time) ";
//
//        Map<String,Object> kt_all = new HashMap<>();
//        for(Integer i=1;i<=20;i++) {
//            String sql_temp1 = sql0_sf1.replace("空调0", "空调" + i );
//            String sql_temp2 = sql0_sf4.replace("空调0", "空调" + i );
//            String sql_temp3 = sql0_sfd.replace("空调0", "空调" + i );
//            String sql_temp4 = sql0_hf1.replace("空调0", "空调" + i );
//            String sql_temp5 = sql0_hf2.replace("空调0", "空调" + i );
//            String sql_temp6 = sql0_hf3.replace("空调0", "空调" + i );
//            String sql_temp7 = sql0_hf4.replace("空调0", "空调" + i );
//            String sql_temp8 = sql0_hfd.replace("空调0", "空调" + i );
//            String sql_temp9 = sql0_ysj1.replace("空调0", "空调" + i );
//            String sql_temp10 = sql0_ysj2.replace("空调0", "空调" + i );
//            String sql_temp11 = sql0_fj1.replace("空调0", "空调" + i );
//            String sql_temp12 = sql0_fj2.replace("空调0", "空调" + i );
//            String sql_temp13 = sql0_p.replace("空调0", "空调" + i );
//
//
//            List<Map<String, Object>> list_kt1 = jdbc.queryForList(sql_temp1); //一台空调所有参数
//            List<Map<String, Object>> list_kt2 = jdbc.queryForList(sql_temp2);
//            List<Map<String, Object>> list_kt3 = jdbc.queryForList(sql_temp3);
//            List<Map<String, Object>> list_kt4 = jdbc.queryForList(sql_temp4);
//            List<Map<String, Object>> list_kt5 = jdbc.queryForList(sql_temp5);
//            List<Map<String, Object>> list_kt6 = jdbc.queryForList(sql_temp6);
//            List<Map<String, Object>> list_kt7 = jdbc.queryForList(sql_temp7);
//            List<Map<String, Object>> list_kt8 = jdbc.queryForList(sql_temp8);
//            List<Map<String, Object>> list_kt9 = jdbc.queryForList(sql_temp9);
//            List<Map<String, Object>> list_kt10 = jdbc.queryForList(sql_temp10);
//            List<Map<String, Object>> list_kt11 = jdbc.queryForList(sql_temp11);
//            List<Map<String, Object>> list_kt12 = jdbc.queryForList(sql_temp12);
//            List<Map<String, Object>> list_kt13 = jdbc.queryForList(sql_temp13);
//
//
//            Map<String, Object> temp = new HashMap<>();
//            List value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt1) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("送风温度4", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt2) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("送风温度1", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt3) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("送风温度设定", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt4) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("回风温度1", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt5) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("回风温度2", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt6) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("回风温度3", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt7) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("回风温度4", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt8) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("回风温度设定", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt9) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("压缩机1容量", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt10) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("压缩机2容量", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt11) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("风机1转速", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt12) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("风机2转速", value);
//
//            value = new ArrayList<>();
//            for (Map<String, Object> p : list_kt13) {
//                Object value_temp = p.get("Value0");
//                value.add(value_temp);
//            }
//            temp.put("空调功率", value);
//            kt_all.put("空调"+i ,temp);
//        }
//
//
//        String sql=" select time from realdata where Location='JF202' and Equipment='空调1' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
//        List <Map<String,Object>> time_list=jdbc.queryForList(sql);
//        List time= new ArrayList();
//        for (Map<String, Object> time0 : time_list) {
//            Object time_temp=time0.get("time");
//            time.add(time_temp);
////            System.out.println(name);
//        }
//        Map<String,Object> ret=new HashMap<>();
//        ret.put("202机房参数",kt_all);
//        ret.put("时间轴",time);
//
//        list_all.add(ret);
//        return list_all;
//    }
//}
