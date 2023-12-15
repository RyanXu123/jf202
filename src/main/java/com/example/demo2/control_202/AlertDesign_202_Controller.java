package com.example.demo2.control_202;

import com.alibaba.fastjson2.JSONObject;
import com.example.demo2.entity.log;
import com.example.demo2.entity.alert;
import com.example.demo2.entity.sitecold;
import com.example.demo2.mapper.logMapper;
import com.example.demo2.service.alertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo2.mapper.sitecoldMapper;

import java.util.*;

@Controller
public class AlertDesign_202_Controller {


    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private alertService alertservice;

    Boolean data_abnormal_alert = true;//数据异常报警
    Boolean real_alert = false;//热点报警
    Boolean coldsite_alert = false;//冷通道波动报警

    Integer time_limit = 6;
    Double cold_unstable_fixed_time = 10.0;
    Double cold_unstable_fixed_range = 3.0;
    @Autowired
    private logMapper logMapper;

    @CrossOrigin
    @PostMapping("/getData/202/realdata/alert_design")
    @ResponseBody
    public JSONObject alertDesign(@RequestBody JSONObject data) {
        String coldsiteAlertLog = "";

        List<Boolean> params_alertDesign=(List<Boolean>) data.get("params");

        List<String> user_alertDesign= (List<String>)data.get("user");


        Boolean coldsiteAlert = params_alertDesign.get(0);

        String userName = user_alertDesign.get(0).toString();
        String userRole = user_alertDesign.get(1).toString();
        String time_operate = user_alertDesign.get(2).toString();

        if (coldsiteAlert.equals(true)) {
            coldsiteAlertLog = "冷通道未报警";
            if (!coldsiteAlert.equals(coldsite_alert)) {

                log logAlert = new log();
                logAlert.setDatacenter_room("JF202");
                logAlert.setContent(coldsiteAlertLog);
                logAlert.setUserName(userName);
                logAlert.setUserRole(userRole);
                logAlert.setTime(time_operate);

                logMapper.insert(logAlert);
            }
        } else if (!coldsiteAlert.equals(false)) {
            coldsiteAlertLog = "冷通道报警";
            if (!coldsiteAlert.equals(coldsite_alert)) {
                log logAlert = new log();
                logAlert.setDatacenter_room("JF202");
                logAlert.setContent(coldsiteAlertLog);
                logAlert.setUserName(userName);
                logAlert.setUserRole(userRole);
                logAlert.setTime(time_operate);

                logMapper.insert(logAlert);
            }

        }
        coldsite_alert = coldsiteAlert;
        return data;
    }


    @CrossOrigin
    @RequestMapping("/getData/202/realdata/alert_design")
    @ResponseBody
    public List<Boolean> alert_design0() {
//        List<Double> ret=new ArrayList<>();
//        real_alert,data_abnormal_alert,
        return Arrays.asList(coldsite_alert);
    }


    //    Integer time_limit=6;
    //    Integer time_limit=6;
    @CrossOrigin
    @PostMapping("/getData/202/dataStatus_time_limit_design")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public Integer time_limit_design(@RequestBody List<String> data) {
//        Integer TIME_design=time_limit*30;
        if (data.isEmpty()) {
            return time_limit / 2;
        }
        Integer TIME_design = Integer.parseInt(data.get(0).toString()) * 2;
        time_limit = TIME_design;
        return time_limit / 2;
    }

    Map<Integer, String> alert_content = new HashMap<>();

    Integer cnt = 0;

    @CrossOrigin
    @RequestMapping("/getData/202/alert")
    @ResponseBody
    public Map<String, Object> alert2() {

        Map<String, Object> b = new HashMap<>();
        List<List<String>> real = new ArrayList<>();
        List<List<String>> data_abnormal_detail = new ArrayList<>();
        List<List<String>> cold_list = new ArrayList<>();
        List<Map<String, Object>> list_data = new ArrayList<>();

//        <sitecold> find_list = new LambdaQueryWrapper<>();
//        find_list.allEq(null);

        Double cold_unstable_fixed_time_real = cold_unstable_fixed_time * 2;


        String sql_hot = "select * from preshow where PointName='冷通道最大温度' ORDER BY id DESC limit 0,7"; //实时警告
        String sql_abnormal = " select * from abnormal_detail where time=(select time from realdata_once order by id desc limit 0,1)";
        String sql_penultimate = "select * from realdata_once where Location='JF202' and Equipment='服务器' and PointName='冷通道温度'  and time = ( SELECT time FROM realdata_once order by time desc limit 1 OFFSET 2000)"; //60数据间隔
        String sql_last = "select * from realdata_once where Location='JF202' and Equipment='服务器' and PointName='冷通道温度' and time = ( SELECT time FROM realdata_once order by time desc limit 1)"; //19测点x3+功率
        sql_penultimate.replace("60", cold_unstable_fixed_time_real.toString());
        Integer siteNum = 19;//测点个数

        List<Map<String, Object>> list_abnormal = jdbc.queryForList(sql_abnormal);
        for (Map<String, Object> m : list_abnormal) {
            data_abnormal_detail.add(Arrays.asList(m.get("time").toString(), "数据异常", m.get("Detail").toString()));

        }

        List<Map<String, Object>> list_hot = jdbc.queryForList(sql_hot);
        for (Map<String, Object> m : list_hot) {
            if (Double.parseDouble(m.get("Value0").toString()) >= 26.8) {
                real.add(Arrays.asList(m.get("time").toString(), m.get("Equipment").toString().substring(3), m.get("PointName").toString() + "为" + String.format("%.2f", m.get("Value0")) + "°C"));
            }
        }


        List<String> server = Arrays.asList("A","B","C","D","E","F","G","H","J","K","L","M","N","P");
        Collections.reverse(server);//从K开始排序

        for (String c : server) {  // 遍历服务器 c 为（"A","B","C","D" ...）

            sql_penultimate = sql_penultimate.replace("'服务器'", "'服务器" + c + "'"); //某服务器所有测点
            sql_last = sql_last.replace("'服务器'", "'服务器" + c + "'");
            List<Map<String, Object>> list_penultimate = jdbc.queryForList(sql_penultimate);
            List<Map<String, Object>> list_last = jdbc.queryForList(sql_last);

            List<Double> server_site_cold_up = new ArrayList<>(); //某列服务器冷通道上测点
            List<Double> server_site_cold_down = new ArrayList<>();  //某列服务器冷通道下测点



            for (int i = 0; i < list_penultimate.size(); i++) {
                // 获取当前测点的上一次和最新一次的温度值
                Double penultimateValue = (double) list_penultimate.get(i).get("Value0");
                Double lastValue = (double) list_last.get(i).get("Value0");
                Double gap = Math.abs(penultimateValue - lastValue);

                if (i % 2 == 0) {
                    // 处理上测点
                    if (lastValue < 1.0) {
                        server_site_cold_up.add(-1.0);
                    } else {
                        server_site_cold_up.add(Math.round(gap * 100.0) / 100.0);
                        if (gap > cold_unstable_fixed_range) {
                            cold_list.add(Arrays.asList(list_last.get(i).get("time").toString(), list_last.get(i).get("Equipment").toString(), "冷通道测点温度波动为" + String.format("%.2f", gap) + "°C"));
                        }
                    }
                } else {
                    // 处理下测点
                    if (lastValue < 0.1) {
                        server_site_cold_down.add(-1.0);
                    } else {
                        server_site_cold_down.add(Math.round(gap * 100.0) / 100.0);
                        if (gap > cold_unstable_fixed_range) {
                            cold_list.add(Arrays.asList(list_last.get(i).get("time").toString(), list_last.get(i).get("Equipment").toString(), "冷通道测点温度波动为" + String.format("%.2f", gap) + "°C"));
                        }
                    }
                }
            }

        }

        List<List<String>> temp= new ArrayList<>();
        if(real_alert==true){//实时报警
            b.put("real_hot",real);
        }else{
            b.put("real_hot",temp);
        }
        if (data_abnormal_alert==true){//数据异常报警
            b.put("data_abnormal_detail",data_abnormal_detail);
        }else{
            b.put("data_abnormal_detail",temp);
        }

        if(coldsite_alert==true){//波动报警
            b.put("cold_change",cold_list);
//            cold_list.clear();
        }else{
            b.put("cold_change",temp);

        }


        String sql_data_alert="select * from data_alert ORDER BY id DESC limit 0,1"; //实时警告

        String sql_data_reasonable="select * from data_reasonable order by Value0 desc limit 6" ;
        sql_data_reasonable.replace("6",time_limit.toString());
//        String sql2="select * from aicmd where CommandType='保底控制' " ;
        List <Map<String,Object>> list_data_reasonable=jdbc.queryForList(sql_data_reasonable);
        Integer cnt=0;
        Integer data_alert=0;
        for(Map<String,Object> c : list_data_reasonable){
            cnt+=Integer.parseInt(c.get("Value0").toString());
        }
        if(cnt>=time_limit){
            data_alert=1;
        }
        b.put("data_alert",data_alert);
        return b;
    }
    /*****************************************coldchange***************************************************************/

    @Autowired
    private sitecoldMapper sitecoldmapper;
    @CrossOrigin
    @RequestMapping("/getData/202/realdata/coldsite_change")
    @ResponseBody
    public List<Map<String,Object>> coldsite_change(){


        List <Map<String,Object>> list_data= new ArrayList<>();  //储存返回的json
        List<String> server = Arrays.asList("A","B","C","D","E","F","G","H","J","K","L","M","N","P");
        Collections.reverse(server);//从P开始排序
        String sql1="select * from realdata_once where Location='JF202' and Equipment='服务器' limit 0,70";

        List <sitecold> list_sitecold= sitecoldmapper.selectList(null);
//        String sql=" select * from realdata_once where Location='JF202' and PointName='冷通道温度' ";
//        List <Map<String,Object>> cold_temp_all=jdbc.queryForList(sql);


        Iterator<sitecold> cold_before_all = list_sitecold.iterator();

//        Iterator<Map<String,Object>> cold_all = cold_temp_all.iterator();
//        cold_list.clear();
        Map<String, Object> servers_cold= new TreeMap<>();  //所有列列服务器冷通道
        Integer siteNum=23;

//        Integer id=0;
        for (String c:server) {  //遍历服务器 c为（"A","B","C","D" ...）
            Map<String, Object> server_temp_cold = new TreeMap<>();  //某列服务器冷通道

            String sql_temp1 = sql1.replace("'服务器'", "'服务器" + c + "'"); //某服务器所有测点
            List<Map<String, Object>> list1 = jdbc.queryForList(sql_temp1);
            List<Double> server_site_cold_up = new ArrayList<>(); //某列服务器冷通道上测点
            List<Double> server_site_cold_down = new ArrayList<>();  //某列服务器冷通道下测点

            Integer cnt_change_site = 0;

            for (Map<String, Object> l : list1) {//遍历每个服务器的测点

                if(cnt_change_site>=siteNum*2){
                    break;
                }
                String SiteName= l.get("SiteName").toString();
                Double value0 = (double) l.get("Value0");
//                Double value_before= Double.parseDouble(cold_all_show.get(SiteName).toString());
                Double value_before=cold_before_all.next().getGapValue(); //下一个测点的值
                if (cnt_change_site < siteNum * 2) {

                    if (cnt_change_site % 2 != 0) {//奇数下测点
                        String s= String.format("%.2f", Math.abs(value_before-value0));
                        double d =Double.parseDouble(s);
                        if(value0>0.0){
                            d =Double.parseDouble(s);
                        }
                        if (value0==0.0){
                            d=-1.0;
                        }
                        server_site_cold_down.add(d);         //正常0
                    } else {
                        String s= String.format("%.2f", Math.abs(value_before-value0));
                        double d =Double.parseDouble(s);
                        if(value0>0.0){
                            d =Double.parseDouble(s);
                        }
                        if (value0==0.0){
                            d=-1.0;
                        }
                        server_site_cold_up.add(d);//冷下 1 3 5 7
                    }
                    cnt_change_site++;
                }
                Map<String, Object> site_cold = new TreeMap<>(); //冷通道
                site_cold.put("up", server_site_cold_up); //某列服务器所有上测点  （up，{服务器所有测点（1，22）（2，22）..}）
                site_cold.put("down", server_site_cold_down);//某列服务器所有下测点  （down，{服务器所有测点（1，22）（2，22）..}）
                servers_cold.put(c, site_cold); //冷通道（A，{(avg,xx),(sitedetail,xx)}）
            }
        }
        list_data.add(servers_cold);

        return list_data;
    }

    @CrossOrigin
    @RequestMapping("/getData/202/realdata/cold_detect_design")
    @ResponseBody
    public List<Double> cold_detect_design(){
        List<Double> ret=new ArrayList<>();
        ret.add(cold_unstable_fixed_range);
        ret.add(cold_unstable_fixed_time);
        return ret;
    }

    @CrossOrigin
    @PostMapping("/getData/202/realdata/cold_detect_design")
    @ResponseBody

    public JSONObject cold_detect_design2(@RequestBody JSONObject data){
        String coldfluctuationtimeLog = "";
        String coldfluctuationRangeLog = "";

        List<Double> params_cold_detect_design2=(List<Double>) data.get("params");

        List<String> user_cold_detect_design2= (List<String>)data.get("user");

        Double coldfluctuationRange = params_cold_detect_design2.get(0);
        Double coldfluctuationTime = params_cold_detect_design2.get(1);

        String userName = user_cold_detect_design2.get(0).toString();
        String userRole = user_cold_detect_design2.get(1).toString();
        String time_operate = user_cold_detect_design2.get(2).toString();

        if (coldfluctuationRange.equals(cold_unstable_fixed_range)) {
            coldfluctuationRangeLog = "冷通道波动范围阈值未改变";
            if (!coldfluctuationRange.equals(cold_unstable_fixed_range)){
                log log1 = new log();
                log1.setDatacenter_room("JF202");
                log1.setContent( coldfluctuationRangeLog);
                log1.setUserName(userName);
                log1.setUserRole(userRole);
                log1.setTime(time_operate);

                logMapper.insert(log1);
            }

        } else if (!coldfluctuationRange.equals(cold_unstable_fixed_range)) {
            coldfluctuationRangeLog ="冷通道波动范围阈值改变为";
            if (!coldfluctuationRange.equals(cold_unstable_fixed_range)){
                log log1 = new log();
                log1.setDatacenter_room("JF202");
                log1.setContent(coldfluctuationRangeLog + "为" + coldfluctuationRange);
                log1.setUserName(userName);
                log1.setUserRole(userRole);
                log1.setTime(time_operate);

                logMapper.insert(log1);
            }
        }
        cold_unstable_fixed_range = coldfluctuationRange;

        if (coldfluctuationTime.equals(cold_unstable_fixed_time)) {
            coldfluctuationtimeLog = "冷通道波动时间阈值未改变";
            if (!coldfluctuationTime.equals(cold_unstable_fixed_time)){
                log log1 = new log();
                log1.setDatacenter_room("JF202");
                log1.setContent(coldfluctuationtimeLog);
                log1.setUserName(userName);
                log1.setUserRole(userRole);
                log1.setTime(time_operate);

                logMapper.insert(log1);
            }

        } else if (!coldfluctuationTime.equals(cold_unstable_fixed_time)) {
            coldfluctuationtimeLog ="冷通道波动时间阈值改变";
            if (!coldfluctuationTime.equals(cold_unstable_fixed_time)){
                log log1 = new log();
                log1.setDatacenter_room("JF202");
                log1.setContent(coldfluctuationtimeLog+"为"+ coldfluctuationTime);
                log1.setUserName(userName);
                log1.setUserRole(userRole);
                log1.setTime(time_operate);

                logMapper.insert(log1);
            }
        }
        cold_unstable_fixed_time = coldfluctuationTime;
        return data;

    }


}

