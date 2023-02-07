package com.example.demo2.control_202;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class dataAnalysis_controller {
    @Autowired
    private JdbcTemplate jdbc;

    @CrossOrigin
    @RequestMapping("/getData/202/dataanalysis")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_data(){
        List <Map<String,Object>> list_all=  new ArrayList<>();
        Map<String, Object> kt1 = new HashMap<String, Object>();
        Map<String, Object> kt2 = new HashMap<String, Object>();
        Map<String, Object> kt3 = new HashMap<String, Object>();
        Map<String, Object> kt4 = new HashMap<String, Object>();
        Map<String, Object> kt5 = new HashMap<String, Object>();
        Map<String, Object> kt6 = new HashMap<String, Object>();
        Map<String, Object> kt7 = new HashMap<String, Object>();
        Map<String, Object> kt8 = new HashMap<String, Object>();
        Map<String, Object> kt9 = new HashMap<String, Object>();
        Map<String, Object> kt10 = new HashMap<String, Object>();
        Map<String, Object> kt11 = new HashMap<String, Object>();
        Map<String, Object> kt12 = new HashMap<String, Object>();
        Map<String, Object> kt13 = new HashMap<String, Object>();
        Map<String, Object> kt14 = new HashMap<String, Object>();
        Map<String, Object> kt15 = new HashMap<String, Object>();
        Map<String, Object> kt16 = new HashMap<String, Object>();
        Map<String, Object> kt17 = new HashMap<String, Object>();
        Map<String, Object> kt18 = new HashMap<String, Object>();
        Map<String, Object> kt19 = new HashMap<String, Object>();
        Map<String, Object> kt20 = new HashMap<String, Object>();
        Map<String, Object> ktall = new HashMap<String, Object>();
        Map<String, Object> dataall = new HashMap<String, Object>();


        String sql1_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调1' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list_temp=jdbc.queryForList(sql1_sf);
        List  list_sf= new ArrayList<>();
        for (Map<String, Object> time0:list_temp) {
            list_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql1_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调1' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list_sfd=new ArrayList<>();
        list_temp=jdbc.queryForList(sql1_sfd);
        for (Map<String, Object> time0:list_temp) {
            list_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql1_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调1' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list_temp=jdbc.queryForList(sql1_hf);
        List  list_hf= new ArrayList<>();
        for (Map<String, Object> time0:list_temp) {
            list_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql1_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调1' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list_hfd=new ArrayList<>();
        list_temp=jdbc.queryForList(sql1_hfd);
        for (Map<String, Object> time0:list_temp) {
            list_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql1_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调1' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list_temp=jdbc.queryForList(sql1_ysj1);
        List  list_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list_temp) {
            list_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql1_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调1' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list_temp=jdbc.queryForList(sql1_ysj2);
        List  list_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list_temp) {
            list_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql1_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调1' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list_temp=jdbc.queryForList(sql1_fj1);
        List  list_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list_temp) {
            list_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql1_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调1'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list_temp=jdbc.queryForList(sql1_fj2);
        List  list_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list_temp) {
            list_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql1_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调1' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list_temp=jdbc.queryForList(sql1_p);
        List  list_p= new ArrayList<>();
        for (Map<String, Object> time0:list_temp) {
            list_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt1.put("送风温度1",list_sf);
        kt1.put("送风温度设定",list_sfd);
        kt1.put("回风温度1",list_hf);
        kt1.put("回风温度设定",list_hfd);
        kt1.put("压缩机1容量",list_ysj1);
        kt1.put("压缩机2容量",list_ysj2);
        kt1.put("风机1转速",list_fj1);
        kt1.put("风机2转速",list_fj2);
        kt1.put("空调功率",list_p);

        ktall.put("空调1",kt1);

















//        kt2
        String sql2_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调2' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list2_temp=jdbc.queryForList(sql2_sf);
        List  list2_sf= new ArrayList<>();
        for (Map<String, Object> time0:list2_temp) {
            list2_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql2_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调2' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list2_sfd=new ArrayList<>();
        list2_temp=jdbc.queryForList(sql2_sfd);
        for (Map<String, Object> time0:list2_temp) {
            list2_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql2_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调2' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list2_temp=jdbc.queryForList(sql2_hf);
        List  list2_hf= new ArrayList<>();
        for (Map<String, Object> time0:list2_temp) {
            list2_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql2_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调2' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list2_hfd=new ArrayList<>();
        list2_temp=jdbc.queryForList(sql2_hfd);
        for (Map<String, Object> time0:list2_temp) {
            list2_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql2_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调2' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list2_temp=jdbc.queryForList(sql2_ysj1);
        List  list2_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list2_temp) {
            list2_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql2_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调2' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list2_temp=jdbc.queryForList(sql2_ysj2);
        List  list2_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list2_temp) {
            list2_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql2_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调2' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list2_temp=jdbc.queryForList(sql2_fj1);
        List  list2_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list2_temp) {
            list2_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql2_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调2'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list2_temp=jdbc.queryForList(sql2_fj2);
        List  list2_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list2_temp) {
            list2_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql2_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调2' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list2_temp=jdbc.queryForList(sql2_p);
        List  list2_p= new ArrayList<>();
        for (Map<String, Object> time0:list2_temp) {
            list2_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt2.put("送风温度1",list2_sf);
        kt2.put("送风温度设定",list2_sfd);
        kt2.put("回风温度1",list2_hf);
        kt2.put("回风温度设定",list2_hfd);
        kt2.put("压缩机1容量",list2_ysj1);
        kt2.put("压缩机2容量",list2_ysj2);
        kt2.put("风机1转速",list2_fj1);
        kt2.put("风机2转速",list2_fj2);
        kt2.put("空调功率",list2_p);

        ktall.put("空调2",kt2);

















        //        kt2
        String sql3_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调3' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list3_temp=jdbc.queryForList(sql3_sf);
        List  list3_sf= new ArrayList<>();
        for (Map<String, Object> time0:list3_temp) {
            list3_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql3_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调3' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list3_sfd=new ArrayList<>();
        list3_temp=jdbc.queryForList(sql3_sfd);
        for (Map<String, Object> time0:list3_temp) {
            list3_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql3_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调3' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list3_temp=jdbc.queryForList(sql3_hf);
        List  list3_hf= new ArrayList<>();
        for (Map<String, Object> time0:list3_temp) {
            list3_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql3_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调3' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list3_hfd=new ArrayList<>();
        list3_temp=jdbc.queryForList(sql3_hfd);
        for (Map<String, Object> time0:list3_temp) {
            list3_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql3_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调3' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list3_temp=jdbc.queryForList(sql3_ysj1);
        List  list3_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list3_temp) {
            list3_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql3_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调3' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list3_temp=jdbc.queryForList(sql3_ysj2);
        List  list3_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list3_temp) {
            list3_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql3_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调3' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list3_temp=jdbc.queryForList(sql3_fj1);
        List  list3_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list3_temp) {
            list3_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql3_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调3'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list3_temp=jdbc.queryForList(sql3_fj2);
        List  list3_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list3_temp) {
            list3_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql3_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调3' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list3_temp=jdbc.queryForList(sql3_p);
        List  list3_p= new ArrayList<>();
        for (Map<String, Object> time0:list3_temp) {
            list3_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt3.put("送风温度1",list3_sf);
        kt3.put("送风温度设定",list3_sfd);
        kt3.put("回风温度1",list3_hf);
        kt3.put("回风温度设定",list3_hfd);
        kt3.put("压缩机1容量",list3_ysj1);
        kt3.put("压缩机2容量",list3_ysj2);
        kt3.put("风机1转速",list3_fj1);
        kt3.put("风机2转速",list3_fj2);
        kt3.put("空调功率",list3_p);

        ktall.put("空调3",kt3);









        //        kt2
        String sql4_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调4' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list4_temp=jdbc.queryForList(sql4_sf);
        List  list4_sf= new ArrayList<>();
        for (Map<String, Object> time0:list4_temp) {
            list4_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql4_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调4' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list4_sfd=new ArrayList<>();
        list4_temp=jdbc.queryForList(sql4_sfd);
        for (Map<String, Object> time0:list4_temp) {
            list4_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql4_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调4' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list4_temp=jdbc.queryForList(sql4_hf);
        List  list4_hf= new ArrayList<>();
        for (Map<String, Object> time0:list4_temp) {
            list4_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql4_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调4' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list4_hfd=new ArrayList<>();
        list4_temp=jdbc.queryForList(sql4_hfd);
        for (Map<String, Object> time0:list4_temp) {
            list4_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql4_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调4' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list4_temp=jdbc.queryForList(sql4_ysj1);
        List  list4_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list4_temp) {
            list4_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql4_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调4' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list4_temp=jdbc.queryForList(sql4_ysj2);
        List  list4_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list4_temp) {
            list4_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql4_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调4' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list4_temp=jdbc.queryForList(sql4_fj1);
        List  list4_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list4_temp) {
            list4_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql4_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调4'  and PointName='风机2转速'  ORDER BY  unix_timestamp(time)    ";
        list4_temp=jdbc.queryForList(sql4_fj2);
        List  list4_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list4_temp) {
            list4_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql4_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调4' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list4_temp=jdbc.queryForList(sql4_p);
        List  list4_p= new ArrayList<>();
        for (Map<String, Object> time0:list4_temp) {
            list4_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt4.put("送风温度1",list4_sf);
        kt4.put("送风温度设定",list4_sfd);
        kt4.put("回风温度1",list4_hf);
        kt4.put("回风温度设定",list4_hfd);
        kt4.put("压缩机1容量",list4_ysj1);
        kt4.put("压缩机2容量",list4_ysj2);
        kt4.put("风机1转速",list4_fj1);
        kt4.put("风机2转速",list4_fj2);
        kt4.put("空调功率",list4_p);

        ktall.put("空调4",kt4);







        //        kt2
        String sql5_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调5' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list5_temp=jdbc.queryForList(sql5_sf);
        List  list5_sf= new ArrayList<>();
        for (Map<String, Object> time0:list5_temp) {
            list5_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql5_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调5' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list5_sfd=new ArrayList<>();
        list5_temp=jdbc.queryForList(sql5_sfd);
        for (Map<String, Object> time0:list5_temp) {
            list5_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql5_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调5' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list5_temp=jdbc.queryForList(sql5_hf);
        List  list5_hf= new ArrayList<>();
        for (Map<String, Object> time0:list5_temp) {
            list5_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql5_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调5' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list5_hfd=new ArrayList<>();
        list5_temp=jdbc.queryForList(sql5_hfd);
        for (Map<String, Object> time0:list5_temp) {
            list5_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql5_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调5' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list5_temp=jdbc.queryForList(sql5_ysj1);
        List  list5_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list5_temp) {
            list5_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql5_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调5' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list5_temp=jdbc.queryForList(sql5_ysj2);
        List  list5_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list5_temp) {
            list5_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql5_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调5' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list5_temp=jdbc.queryForList(sql5_fj1);
        List  list5_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list5_temp) {
            list5_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql5_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调5'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list5_temp=jdbc.queryForList(sql5_fj2);
        List  list5_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list5_temp) {
            list5_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql5_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调5' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list5_temp=jdbc.queryForList(sql5_p);
        List  list5_p= new ArrayList<>();
        for (Map<String, Object> time0:list5_temp) {
            list5_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt5.put("送风温度1",list5_sf);
        kt5.put("送风温度设定",list5_sfd);
        kt5.put("回风温度1",list5_hf);
        kt5.put("回风温度设定",list5_hfd);
        kt5.put("压缩机1容量",list5_ysj1);
        kt5.put("压缩机2容量",list5_ysj2);
        kt5.put("风机1转速",list5_fj1);
        kt5.put("风机2转速",list5_fj2);
        kt5.put("空调功率",list5_p);

        ktall.put("空调5",kt5);



        //        kt2
        String sql6_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调6' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list6_temp=jdbc.queryForList(sql6_sf);
        List  list6_sf= new ArrayList<>();
        for (Map<String, Object> time0:list6_temp) {
            list6_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql6_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调6' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list6_sfd=new ArrayList<>();
        list6_temp=jdbc.queryForList(sql6_sfd);
        for (Map<String, Object> time0:list6_temp) {
            list6_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql6_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调6' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list6_temp=jdbc.queryForList(sql6_hf);
        List  list6_hf= new ArrayList<>();
        for (Map<String, Object> time0:list6_temp) {
            list6_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql6_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调6' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list6_hfd=new ArrayList<>();
        list6_temp=jdbc.queryForList(sql6_hfd);
        for (Map<String, Object> time0:list6_temp) {
            list6_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql6_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调6' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list6_temp=jdbc.queryForList(sql6_ysj1);
        List  list6_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list6_temp) {
            list6_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql6_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调6' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list6_temp=jdbc.queryForList(sql6_ysj2);
        List  list6_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list6_temp) {
            list6_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql6_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调6' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list6_temp=jdbc.queryForList(sql6_fj1);
        List  list6_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list6_temp) {
            list6_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql6_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调6'  and PointName='风机2转速'  ORDER BY  unix_timestamp(time)    ";
        list6_temp=jdbc.queryForList(sql6_fj2);
        List  list6_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list6_temp) {
            list6_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql6_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调6' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list6_temp=jdbc.queryForList(sql6_p);
        List  list6_p= new ArrayList<>();
        for (Map<String, Object> time0:list6_temp) {
            list6_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt6.put("送风温度1",list6_sf);
        kt6.put("送风温度设定",list6_sfd);
        kt6.put("回风温度1",list6_hf);
        kt6.put("回风温度设定",list6_hfd);
        kt6.put("压缩机1容量",list6_ysj1);
        kt6.put("压缩机2容量",list6_ysj2);
        kt6.put("风机1转速",list6_fj1);
        kt6.put("风机2转速",list6_fj2);
        kt6.put("空调功率",list6_p);

        ktall.put("空调6",kt6);
        




        //        kt2
        String sql7_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调7' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list7_temp=jdbc.queryForList(sql7_sf);
        List  list7_sf= new ArrayList<>();
        for (Map<String, Object> time0:list7_temp) {
            list7_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql7_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调7' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list7_sfd=new ArrayList<>();
        list7_temp=jdbc.queryForList(sql7_sfd);
        for (Map<String, Object> time0:list7_temp) {
            list7_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql7_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调7' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list7_temp=jdbc.queryForList(sql7_hf);
        List  list7_hf= new ArrayList<>();
        for (Map<String, Object> time0:list7_temp) {
            list7_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql7_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调7' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list7_hfd=new ArrayList<>();
        list7_temp=jdbc.queryForList(sql7_hfd);
        for (Map<String, Object> time0:list7_temp) {
            list7_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql7_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调7' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list7_temp=jdbc.queryForList(sql7_ysj1);
        List  list7_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list7_temp) {
            list7_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql7_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调7' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list7_temp=jdbc.queryForList(sql7_ysj2);
        List  list7_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list7_temp) {
            list7_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql7_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调7' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list7_temp=jdbc.queryForList(sql7_fj1);
        List  list7_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list7_temp) {
            list7_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql7_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调7'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list7_temp=jdbc.queryForList(sql7_fj2);
        List  list7_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list7_temp) {
            list7_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql7_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调7' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list7_temp=jdbc.queryForList(sql7_p);
        List  list7_p= new ArrayList<>();
        for (Map<String, Object> time0:list7_temp) {
            list7_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt7.put("送风温度1",list7_sf);
        kt7.put("送风温度设定",list7_sfd);
        kt7.put("回风温度1",list7_hf);
        kt7.put("回风温度设定",list7_hfd);
        kt7.put("压缩机1容量",list7_ysj1);
        kt7.put("压缩机2容量",list7_ysj2);
        kt7.put("风机1转速",list7_fj1);
        kt7.put("风机2转速",list7_fj2);
        kt7.put("空调功率",list7_p);
        ktall.put("空调7",kt7);




        //        kt2
        String sql8_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调8' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list8_temp=jdbc.queryForList(sql8_sf);
        List  list8_sf= new ArrayList<>();
        for (Map<String, Object> time0:list8_temp) {
            list8_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql8_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调8' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list8_sfd=new ArrayList<>();
        list8_temp=jdbc.queryForList(sql8_sfd);
        for (Map<String, Object> time0:list8_temp) {
            list8_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql8_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调8' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list8_temp=jdbc.queryForList(sql8_hf);
        List  list8_hf= new ArrayList<>();
        for (Map<String, Object> time0:list8_temp) {
            list8_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql8_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调8' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list8_hfd=new ArrayList<>();
        list8_temp=jdbc.queryForList(sql8_hfd);
        for (Map<String, Object> time0:list8_temp) {
            list8_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql8_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调8' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list8_temp=jdbc.queryForList(sql8_ysj1);
        List  list8_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list8_temp) {
            list8_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql8_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调8' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list8_temp=jdbc.queryForList(sql8_ysj2);
        List  list8_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list8_temp) {
            list8_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql8_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调8' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list8_temp=jdbc.queryForList(sql8_fj1);
        List  list8_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list8_temp) {
            list8_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql8_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调8'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list8_temp=jdbc.queryForList(sql8_fj2);
        List  list8_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list8_temp) {
            list8_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql8_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调8' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list8_temp=jdbc.queryForList(sql8_p);
        List  list8_p= new ArrayList<>();
        for (Map<String, Object> time0:list8_temp) {
            list8_p.add(time0.get("Value0"));
//            System.out.println(name);
        }
        kt8.put("送风温度1",list8_sf);
        kt8.put("送风温度设定",list8_sfd);
        kt8.put("回风温度1",list8_hf);
        kt8.put("回风温度设定",list8_hfd);
        kt8.put("压缩机1容量",list8_ysj1);
        kt8.put("压缩机2容量",list8_ysj2);
        kt8.put("风机1转速",list8_fj1);
        kt8.put("风机2转速",list8_fj2);
        kt8.put("空调功率",list8_p);

        ktall.put("空调8",kt8);





        String sql9_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调9' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list9_temp=jdbc.queryForList(sql9_sf);
        List  list9_sf= new ArrayList<>();
        for (Map<String, Object> time0:list9_temp) {
            list9_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql9_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调9' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list9_sfd=new ArrayList<>();
        list9_temp=jdbc.queryForList(sql9_sfd);
        for (Map<String, Object> time0:list9_temp) {
            list9_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql9_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调9' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list9_temp=jdbc.queryForList(sql9_hf);
        List  list9_hf= new ArrayList<>();
        for (Map<String, Object> time0:list9_temp) {
            list9_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql9_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调9' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list9_hfd=new ArrayList<>();
        list9_temp=jdbc.queryForList(sql9_hfd);
        for (Map<String, Object> time0:list9_temp) {
            list9_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql9_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调9' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list9_temp=jdbc.queryForList(sql9_ysj1);
        List  list9_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list9_temp) {
            list9_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql9_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调9' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list9_temp=jdbc.queryForList(sql9_ysj2);
        List  list9_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list9_temp) {
            list9_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql9_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调9' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list9_temp=jdbc.queryForList(sql9_fj1);
        List  list9_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list9_temp) {
            list9_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql9_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调9'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list9_temp=jdbc.queryForList(sql9_fj2);
        List  list9_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list9_temp) {
            list9_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql9_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调9' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list9_temp=jdbc.queryForList(sql9_p);
        List  list9_p= new ArrayList<>();
        for (Map<String, Object> time0:list9_temp) {
            list9_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt1.put("送风温度1",list9_sf);
        kt1.put("送风温度设定",list9_sfd);
        kt1.put("回风温度1",list9_hf);
        kt1.put("回风温度设定",list9_hfd);
        kt1.put("压缩机1容量",list9_ysj1);
        kt1.put("压缩机2容量",list9_ysj2);
        kt1.put("风机1转速",list9_fj1);
        kt1.put("风机2转速",list9_fj2);
        kt1.put("空调功率",list9_p);

        ktall.put("空调9",kt9);



        //        kt2
        String sql10_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调10' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list10_temp=jdbc.queryForList(sql10_sf);
        List  list10_sf= new ArrayList<>();
        for (Map<String, Object> time0:list10_temp) {
            list10_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql10_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调10' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list10_sfd=new ArrayList<>();
        list10_temp=jdbc.queryForList(sql10_sfd);
        for (Map<String, Object> time0:list10_temp) {
            list10_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql10_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调10' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list10_temp=jdbc.queryForList(sql10_hf);
        List  list10_hf= new ArrayList<>();
        for (Map<String, Object> time0:list10_temp) {
            list10_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql10_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调10' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list10_hfd=new ArrayList<>();
        list10_temp=jdbc.queryForList(sql10_hfd);
        for (Map<String, Object> time0:list10_temp) {
            list10_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql10_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调10' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list10_temp=jdbc.queryForList(sql10_ysj1);
        List  list10_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list10_temp) {
            list10_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql10_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调10' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list10_temp=jdbc.queryForList(sql10_ysj2);
        List  list10_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list10_temp) {
            list10_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql10_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调10' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list10_temp=jdbc.queryForList(sql10_fj1);
        List  list10_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list10_temp) {
            list10_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql10_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调10'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list10_temp=jdbc.queryForList(sql10_fj2);
        List  list10_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list10_temp) {
            list10_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql10_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调10' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list10_temp=jdbc.queryForList(sql10_p);
        List  list10_p= new ArrayList<>();
        for (Map<String, Object> time0:list10_temp) {
            list10_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt10.put("送风温度1",list10_sf);
        kt10.put("送风温度设定",list10_sfd);
        kt10.put("回风温度1",list10_hf);
        kt10.put("回风温度设定",list10_hfd);
        kt10.put("压缩机1容量",list10_ysj1);
        kt10.put("压缩机2容量",list10_ysj2);
        kt10.put("风机1转速",list10_fj1);
        kt10.put("风机2转速",list10_fj2);
        kt10.put("空调功率",list10_p);

        ktall.put("空调10",kt10);
















        //        kt2
        String sql11_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调11' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list11_temp=jdbc.queryForList(sql11_sf);
        List  list11_sf= new ArrayList<>();
        for (Map<String, Object> time0:list11_temp) {
            list11_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql11_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调11' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list11_sfd=new ArrayList<>();
        list11_temp=jdbc.queryForList(sql11_sfd);
        for (Map<String, Object> time0:list11_temp) {
            list11_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql11_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调11' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list11_temp=jdbc.queryForList(sql11_hf);
        List  list11_hf= new ArrayList<>();
        for (Map<String, Object> time0:list11_temp) {
            list11_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql11_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调11' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list11_hfd=new ArrayList<>();
        list11_temp=jdbc.queryForList(sql11_hfd);
        for (Map<String, Object> time0:list11_temp) {
            list11_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql11_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调11' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list11_temp=jdbc.queryForList(sql11_ysj1);
        List  list11_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list11_temp) {
            list11_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql11_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调11' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list11_temp=jdbc.queryForList(sql11_ysj2);
        List  list11_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list11_temp) {
            list11_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql11_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调11' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list11_temp=jdbc.queryForList(sql11_fj1);
        List  list11_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list11_temp) {
            list11_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql11_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调11'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list11_temp=jdbc.queryForList(sql11_fj2);
        List  list11_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list11_temp) {
            list11_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql11_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调11' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list11_temp=jdbc.queryForList(sql11_p);
        List  list11_p= new ArrayList<>();
        for (Map<String, Object> time0:list11_temp) {
            list11_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt11.put("送风温度1",list11_sf);
        kt11.put("送风温度设定",list11_sfd);
        kt11.put("回风温度1",list11_hf);
        kt11.put("回风温度设定",list11_hfd);
        kt11.put("压缩机1容量",list11_ysj1);
        kt11.put("压缩机2容量",list11_ysj2);
        kt11.put("风机1转速",list11_fj1);
        kt11.put("风机2转速",list11_fj2);
        kt11.put("空调功率",list11_p);

        ktall.put("空调11",kt11);

















        //        kt2
        String sql12_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调12' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list12_temp=jdbc.queryForList(sql12_sf);
        List  list12_sf= new ArrayList<>();
        for (Map<String, Object> time0:list12_temp) {
            list12_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql12_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调12' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list12_sfd=new ArrayList<>();
        list12_temp=jdbc.queryForList(sql12_sfd);
        for (Map<String, Object> time0:list12_temp) {
            list12_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql12_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调12' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list12_temp=jdbc.queryForList(sql12_hf);
        List  list12_hf= new ArrayList<>();
        for (Map<String, Object> time0:list12_temp) {
            list12_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql12_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调12' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list12_hfd=new ArrayList<>();
        list12_temp=jdbc.queryForList(sql12_hfd);
        for (Map<String, Object> time0:list12_temp) {
            list12_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql12_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调12' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list12_temp=jdbc.queryForList(sql12_ysj1);
        List  list12_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list12_temp) {
            list12_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql12_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调12' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list12_temp=jdbc.queryForList(sql12_ysj2);
        List  list12_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list12_temp) {
            list12_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql12_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调12' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list12_temp=jdbc.queryForList(sql12_fj1);
        List  list12_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list12_temp) {
            list12_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql12_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调12'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list12_temp=jdbc.queryForList(sql12_fj2);
        List  list12_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list12_temp) {
            list12_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql12_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调12' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list12_temp=jdbc.queryForList(sql12_p);
        List  list12_p= new ArrayList<>();
        for (Map<String, Object> time0:list12_temp) {
            list12_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt12.put("送风温度1",list12_sf);
        kt12.put("送风温度设定",list12_sfd);
        kt12.put("回风温度1",list12_hf);
        kt12.put("回风温度设定",list12_hfd);
        kt12.put("压缩机1容量",list12_ysj1);
        kt12.put("压缩机2容量",list12_ysj2);
        kt12.put("风机1转速",list12_fj1);
        kt12.put("风机2转速",list12_fj2);
        kt12.put("空调功率",list12_p);

        ktall.put("空调12",kt12);













        //        kt2
        String sql13_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调13' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list13_temp=jdbc.queryForList(sql13_sf);
        List  list13_sf= new ArrayList<>();
        for (Map<String, Object> time0:list13_temp) {
            list13_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql13_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调13' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list13_sfd=new ArrayList<>();
        list13_temp=jdbc.queryForList(sql13_sfd);
        for (Map<String, Object> time0:list13_temp) {
            list13_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql13_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调13' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list13_temp=jdbc.queryForList(sql13_hf);
        List  list13_hf= new ArrayList<>();
        for (Map<String, Object> time0:list13_temp) {
            list13_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql13_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调13' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list13_hfd=new ArrayList<>();
        list13_temp=jdbc.queryForList(sql13_hfd);
        for (Map<String, Object> time0:list13_temp) {
            list13_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql13_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调13' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list13_temp=jdbc.queryForList(sql13_ysj1);
        List  list13_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list13_temp) {
            list13_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql13_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调13' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list13_temp=jdbc.queryForList(sql13_ysj2);
        List  list13_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list13_temp) {
            list13_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql13_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调13' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list13_temp=jdbc.queryForList(sql13_fj1);
        List  list13_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list13_temp) {
            list13_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql13_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调13'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list13_temp=jdbc.queryForList(sql13_fj2);
        List  list13_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list13_temp) {
            list13_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql13_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调13' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list13_temp=jdbc.queryForList(sql13_p);
        List  list13_p= new ArrayList<>();
        for (Map<String, Object> time0:list13_temp) {
            list13_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt13.put("送风温度1",list13_sf);
        kt13.put("送风温度设定",list13_sfd);
        kt13.put("回风温度1",list13_hf);
        kt13.put("回风温度设定",list13_hfd);
        kt13.put("压缩机1容量",list13_ysj1);
        kt13.put("压缩机2容量",list13_ysj2);
        kt13.put("风机1转速",list13_fj1);
        kt13.put("风机2转速",list13_fj2);
        kt13.put("空调功率",list13_p);

        ktall.put("空调13",kt13);













        //        kt2
        String sql14_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调14' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list14_temp=jdbc.queryForList(sql14_sf);
        List  list14_sf= new ArrayList<>();
        for (Map<String, Object> time0:list14_temp) {
            list14_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql14_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调14' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list14_sfd=new ArrayList<>();
        list14_temp=jdbc.queryForList(sql14_sfd);
        for (Map<String, Object> time0:list14_temp) {
            list14_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql14_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调14' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list14_temp=jdbc.queryForList(sql14_hf);
        List  list14_hf= new ArrayList<>();
        for (Map<String, Object> time0:list14_temp) {
            list14_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql14_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调14' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list14_hfd=new ArrayList<>();
        list14_temp=jdbc.queryForList(sql14_hfd);
        for (Map<String, Object> time0:list14_temp) {
            list14_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql14_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调14' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list14_temp=jdbc.queryForList(sql14_ysj1);
        List  list14_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list14_temp) {
            list14_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql14_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调14' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list14_temp=jdbc.queryForList(sql14_ysj2);
        List  list14_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list14_temp) {
            list14_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql14_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调14' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list14_temp=jdbc.queryForList(sql14_fj1);
        List  list14_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list14_temp) {
            list14_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql14_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调14'  and PointName= '风机2转速' ORDER BY  unix_timestamp(time)    ";
        list14_temp=jdbc.queryForList(sql14_fj2);
        List  list14_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list14_temp) {
            list14_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql14_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调14' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list14_temp=jdbc.queryForList(sql14_p);
        List  list14_p= new ArrayList<>();
        for (Map<String, Object> time0:list14_temp) {
            list14_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt14.put("送风温度1",list14_sf);
        kt14.put("送风温度设定",list14_sfd);
        kt14.put("回风温度1",list14_hf);
        kt14.put("回风温度设定",list14_hfd);
        kt14.put("压缩机1容量",list14_ysj1);
        kt14.put("压缩机2容量",list14_ysj2);
        kt14.put("风机1转速",list14_fj1);
        kt14.put("风机2转速",list14_fj2);
        kt14.put("空调功率",list14_p);

        ktall.put("空调14",kt14);





















        //        kt2
        String sql15_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调15' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list15_temp=jdbc.queryForList(sql15_sf);
        List  list15_sf= new ArrayList<>();
        for (Map<String, Object> time0:list15_temp) {
            list15_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql15_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调15' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list15_sfd=new ArrayList<>();
        list15_temp=jdbc.queryForList(sql15_sfd);
        for (Map<String, Object> time0:list15_temp) {
            list15_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql15_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调15' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list15_temp=jdbc.queryForList(sql15_hf);
        List  list15_hf= new ArrayList<>();
        for (Map<String, Object> time0:list15_temp) {
            list15_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql15_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调15' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list15_hfd=new ArrayList<>();
        list15_temp=jdbc.queryForList(sql15_hfd);
        for (Map<String, Object> time0:list15_temp) {
            list15_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql15_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调15' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list15_temp=jdbc.queryForList(sql15_ysj1);
        List  list15_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list15_temp) {
            list15_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql15_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调15' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list15_temp=jdbc.queryForList(sql15_ysj2);
        List  list15_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list15_temp) {
            list15_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql15_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调15' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list15_temp=jdbc.queryForList(sql15_fj1);
        List  list15_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list15_temp) {
            list15_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql15_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调15'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list15_temp=jdbc.queryForList(sql15_fj2);
        List  list15_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list15_temp) {
            list15_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql15_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调15' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list15_temp=jdbc.queryForList(sql15_p);
        List  list15_p= new ArrayList<>();
        for (Map<String, Object> time0:list15_temp) {
            list15_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt15.put("送风温度1",list15_sf);
        kt15.put("送风温度设定",list15_sfd);
        kt15.put("回风温度1",list15_hf);
        kt15.put("回风温度设定",list15_hfd);
        kt15.put("压缩机1容量",list15_ysj1);
        kt15.put("压缩机2容量",list15_ysj2);
        kt15.put("风机1转速",list15_fj1);
        kt15.put("风机2转速",list15_fj2);
        kt15.put("空调功率",list15_p);

        ktall.put("空调15",kt15);


















        //        kt2
        String sql16_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调16' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list16_temp=jdbc.queryForList(sql16_sf);
        List  list16_sf= new ArrayList<>();
        for (Map<String, Object> time0:list16_temp) {
            list16_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql16_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调16' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list16_sfd=new ArrayList<>();
        list16_temp=jdbc.queryForList(sql16_sfd);
        for (Map<String, Object> time0:list16_temp) {
            list16_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql16_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调16' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list16_temp=jdbc.queryForList(sql16_hf);
        List  list16_hf= new ArrayList<>();
        for (Map<String, Object> time0:list16_temp) {
            list16_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql16_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调16' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list16_hfd=new ArrayList<>();
        list16_temp=jdbc.queryForList(sql16_hfd);
        for (Map<String, Object> time0:list16_temp) {
            list16_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql16_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调16' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list16_temp=jdbc.queryForList(sql16_ysj1);
        List  list16_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list16_temp) {
            list16_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql16_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调16' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list16_temp=jdbc.queryForList(sql16_ysj2);
        List  list16_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list16_temp) {
            list16_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql16_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调16' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list16_temp=jdbc.queryForList(sql16_fj1);
        List  list16_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list16_temp) {
            list16_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql16_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调16'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list16_temp=jdbc.queryForList(sql16_fj2);
        List  list16_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list16_temp) {
            list16_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql16_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调16' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list16_temp=jdbc.queryForList(sql16_p);
        List  list16_p= new ArrayList<>();
        for (Map<String, Object> time0:list16_temp) {
            list16_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt16.put("送风温度1",list16_sf);
        kt16.put("送风温度设定",list16_sfd);
        kt16.put("回风温度1",list16_hf);
        kt16.put("回风温度设定",list16_hfd);
        kt16.put("压缩机1容量",list16_ysj1);
        kt16.put("压缩机2容量",list16_ysj2);
        kt16.put("风机1转速",list16_fj1);
        kt16.put("风机2转速",list16_fj2);
        kt16.put("空调功率",list16_p);

        ktall.put("空调16",kt16);

















        //        kt2
        String sql17_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调17' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list17_temp=jdbc.queryForList(sql17_sf);
        List  list17_sf= new ArrayList<>();
        for (Map<String, Object> time0:list17_temp) {
            list17_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql17_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调17' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list17_sfd=new ArrayList<>();
        list17_temp=jdbc.queryForList(sql17_sfd);
        for (Map<String, Object> time0:list17_temp) {
            list17_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql17_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调17' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list17_temp=jdbc.queryForList(sql17_hf);
        List  list17_hf= new ArrayList<>();
        for (Map<String, Object> time0:list17_temp) {
            list17_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql17_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调17' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list17_hfd=new ArrayList<>();
        list17_temp=jdbc.queryForList(sql17_hfd);
        for (Map<String, Object> time0:list17_temp) {
            list17_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql17_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调17' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list17_temp=jdbc.queryForList(sql17_ysj1);
        List  list17_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list17_temp) {
            list17_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql17_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调17' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list17_temp=jdbc.queryForList(sql17_ysj2);
        List  list17_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list17_temp) {
            list17_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql17_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调17' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list17_temp=jdbc.queryForList(sql17_fj1);
        List  list17_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list17_temp) {
            list17_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql17_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调17'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list17_temp=jdbc.queryForList(sql17_fj2);
        List  list17_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list17_temp) {
            list17_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql17_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调17' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list17_temp=jdbc.queryForList(sql17_p);
        List  list17_p= new ArrayList<>();
        for (Map<String, Object> time0:list17_temp) {
            list17_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt17.put("送风温度1",list17_sf);
        kt17.put("送风温度设定",list17_sfd);
        kt17.put("回风温度1",list17_hf);
        kt17.put("回风温度设定",list17_hfd);
        kt17.put("压缩机1容量",list17_ysj1);
        kt17.put("压缩机2容量",list17_ysj2);
        kt17.put("风机1转速",list17_fj1);
        kt17.put("风机2转速",list17_fj2);
        kt17.put("空调功率",list17_p);

        ktall.put("空调17",kt17);
















        //        kt2
        String sql18_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调18' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list18_temp=jdbc.queryForList(sql18_sf);
        List  list18_sf= new ArrayList<>();
        for (Map<String, Object> time0:list18_temp) {
            list18_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql18_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调18' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)   ";
        List list18_sfd=new ArrayList<>();
        list18_temp=jdbc.queryForList(sql18_sfd);
        for (Map<String, Object> time0:list18_temp) {
            list18_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql18_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调18' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list18_temp=jdbc.queryForList(sql18_hf);
        List  list18_hf= new ArrayList<>();
        for (Map<String, Object> time0:list18_temp) {
            list18_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql18_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调18' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list18_hfd=new ArrayList<>();
        list18_temp=jdbc.queryForList(sql18_hfd);
        for (Map<String, Object> time0:list18_temp) {
            list18_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql18_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调18' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list18_temp=jdbc.queryForList(sql18_ysj1);
        List  list18_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list18_temp) {
            list18_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql18_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调18' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list18_temp=jdbc.queryForList(sql18_ysj2);
        List  list18_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list18_temp) {
            list18_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql18_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调18' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list18_temp=jdbc.queryForList(sql18_fj1);
        List  list18_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list18_temp) {
            list18_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql18_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调18'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list18_temp=jdbc.queryForList(sql18_fj2);
        List  list18_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list18_temp) {
            list18_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql18_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调18' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list18_temp=jdbc.queryForList(sql18_p);
        List  list18_p= new ArrayList<>();
        for (Map<String, Object> time0:list18_temp) {
            list18_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt18.put("送风温度1",list18_sf);
        kt18.put("送风温度设定",list18_sfd);
        kt18.put("回风温度1",list18_hf);
        kt18.put("回风温度设定",list18_hfd);
        kt18.put("压缩机1容量",list18_ysj1);
        kt18.put("压缩机2容量",list18_ysj2);
        kt18.put("风机1转速",list18_fj1);
        kt18.put("风机2转速",list18_fj2);
        kt18.put("空调功率",list18_p);

        ktall.put("空调18",kt18);















        //        kt2
        String sql19_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调19' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list19_temp=jdbc.queryForList(sql19_sf);
        List  list19_sf= new ArrayList<>();
        for (Map<String, Object> time0:list19_temp) {
            list19_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql19_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调19' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)    ";
        List list19_sfd=new ArrayList<>();
        list19_temp=jdbc.queryForList(sql19_sfd);
        for (Map<String, Object> time0:list19_temp) {
            list19_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql19_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调19' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list19_temp=jdbc.queryForList(sql19_hf);
        List  list19_hf= new ArrayList<>();
        for (Map<String, Object> time0:list19_temp) {
            list19_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql19_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调19' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list19_hfd=new ArrayList<>();
        list19_temp=jdbc.queryForList(sql19_hfd);
        for (Map<String, Object> time0:list19_temp) {
            list19_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql19_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调19' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list19_temp=jdbc.queryForList(sql19_ysj1);
        List  list19_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list19_temp) {
            list19_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql19_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调19' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list19_temp=jdbc.queryForList(sql19_ysj2);
        List  list19_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list19_temp) {
            list19_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql19_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调19' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list19_temp=jdbc.queryForList(sql19_fj1);
        List  list19_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list19_temp) {
            list19_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql19_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调19'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)   ";
        list19_temp=jdbc.queryForList(sql19_fj2);
        List  list19_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list19_temp) {
            list19_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql19_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调19' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list19_temp=jdbc.queryForList(sql19_p);
        List  list19_p= new ArrayList<>();
        for (Map<String, Object> time0:list19_temp) {
            list19_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt19.put("送风温度1",list19_sf);
        kt19.put("送风温度设定",list19_sfd);
        kt19.put("回风温度1",list19_hf);
        kt19.put("回风温度设定",list19_hfd);
        kt19.put("压缩机1容量",list19_ysj1);
        kt19.put("压缩机2容量",list19_ysj2);
        kt19.put("风机1转速",list19_fj1);
        kt19.put("风机2转速",list19_fj2);
        kt19.put("空调功率",list19_p);

        ktall.put("空调19",kt19);


















        //        kt2
        String sql20_sf=" select Value0 from realdata where Location='JF202' and Equipment='空调20' and PointName='送风温度1' ORDER BY  unix_timestamp(time)    ";
        List <Map<String, Object>> list20_temp=jdbc.queryForList(sql20_sf);
        List  list20_sf= new ArrayList<>();
        for (Map<String, Object> time0:list20_temp) {
            list20_sf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql20_sfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调20' and PointName='送风温度设定'  ORDER BY  unix_timestamp(time)   ";
        List list20_sfd=new ArrayList<>();
        list20_temp=jdbc.queryForList(sql20_sfd);
        for (Map<String, Object> time0:list20_temp) {
            list20_sfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql20_hf=" select Value0,time from realdata where Location='JF202' and Equipment='空调20' and PointName='回风温度1' ORDER BY  unix_timestamp(time)    ";
        list20_temp=jdbc.queryForList(sql20_hf);
        List  list20_hf= new ArrayList<>();
        for (Map<String, Object> time0:list20_temp) {
            list20_hf.add(time0.get("Value0"));
//            System.out.println(name);
        }
        String sql20_hfd=" select Value0,time from realdata where Location='JF202' and Equipment='空调20' and PointName='回风温度设定' ORDER BY  unix_timestamp(time)    ";
        List  list20_hfd=new ArrayList<>();
        list20_temp=jdbc.queryForList(sql20_hfd);
        for (Map<String, Object> time0:list20_temp) {
            list20_hfd.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql20_ysj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调20' and PointName='压缩机1容量' ORDER BY  unix_timestamp(time)    ";
        list20_temp=jdbc.queryForList(sql20_ysj1);
        List  list20_ysj1= new ArrayList<>();
        for (Map<String, Object> time0:list20_temp) {
            list20_ysj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql20_ysj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调20' and PointName='压缩机2容量' ORDER BY  unix_timestamp(time)    ";
        list20_temp=jdbc.queryForList(sql20_ysj2);
        List  list20_ysj2= new ArrayList<>();
        for (Map<String, Object> time0:list20_temp) {
            list20_ysj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql20_fj1=" select Value0,time from realdata where Location='JF202' and Equipment='空调20' and PointName='风机1转速' ORDER BY  unix_timestamp(time)    ";
        list20_temp=jdbc.queryForList(sql20_fj1);
        List  list20_fj1= new ArrayList<>();
        for (Map<String, Object> time0:list20_temp) {
            list20_fj1.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql20_fj2=" select Value0,time from realdata where Location='JF202' and Equipment='空调20'  and PointName='风机2转速' ORDER BY  unix_timestamp(time)    ";
        list20_temp=jdbc.queryForList(sql20_fj2);
        List  list20_fj2= new ArrayList<>();
        for (Map<String, Object> time0:list20_temp) {
            list20_fj2.add(time0.get("Value0"));
//            System.out.println(name);
        }

        String sql20_p=" select Value0,time from realdata where Location='JF202' and Equipment='空调20' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        list20_temp=jdbc.queryForList(sql20_p);
        List  list20_p= new ArrayList<>();
        for (Map<String, Object> time0:list20_temp) {
            list20_p.add(time0.get("Value0"));
//            System.out.println(name);
        }


        kt20.put("送风温度1",list20_sf);
        kt20.put("送风温度设定",list20_sfd);
        kt20.put("回风温度1",list20_hf);
        kt20.put("回风温度设定",list20_hfd);
        kt20.put("压缩机1容量",list20_ysj1);
        kt20.put("压缩机2容量",list20_ysj2);
        kt20.put("风机1转速",list20_fj1);
        kt20.put("风机2转速",list20_fj2);
        kt20.put("空调功率",list20_p);

        ktall.put("空调20",kt20);




















//        String str = "2019-03-13 13:54:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sql=" select time from realdata where Location='JF202' and Equipment='空调7' and PointName='空调功率' ORDER BY  unix_timestamp(time)    ";
        List <Map<String,Object>> time_list=jdbc.queryForList(sql);
        List time= new ArrayList();
        for (Map<String, Object> time0 : time_list) {
            Object time_temp=time0.get("time");
//            try {
//                time_temp=simpleDateFormat.parse(time_temp.toString());
//                time.add(time_temp);
//            }catch (Exception e){
//                time.add(time_temp);
//            }
            time.add(time_temp);
//            System.out.println(name);
        }
        

        dataall.put("空调",ktall);
        dataall.put("time",time);
        list_all.add((dataall));

        return list_all;
    }
}
