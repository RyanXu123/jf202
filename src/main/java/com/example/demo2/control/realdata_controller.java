package com.example.demo2.control;


import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.ListableBeanFactoryExtensionsKt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.ldap.SortKey;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class realdata_controller {

    @Autowired
    private JdbcTemplate jdbc;


    @CrossOrigin
    @RequestMapping("/getData/202/realtime/ktall")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_kt(){
        String sql="select Equipment,PointName,SiteName,Value0 from realdata_once where Location='JF202' and EquipmentType='KT'";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }

    @CrossOrigin
    @RequestMapping("/getData/202/realtime/ktparams")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_ktparams(){
        String sql="select Equipment,SiteName from realdata_once where Location='JF202' and PointName='所有参数' limit 0,20";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }

    @CrossOrigin
    @RequestMapping("/getData/202/realtime/serverall")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_server(){
        String sql="select Equipment,PointName,SiteName,Value0 from realdata_once where Location='JF202' and EquipmentType='Server'";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }

    @CrossOrigin
    @RequestMapping("/getData/202/realtime/serveravgcold")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_serveravg(){
        String sql="select Equipment,PointName,SiteName from realdata_once where Location='JF202'  and PointName='冷通道上平均' order by Equipment limit 0,14";
        String sql2="select Equipment,PointName,SiteName from realdata_once where Location='JF202'  and PointName='冷通道下平均' order by Equipment limit 0,14";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        List <Map<String,Object>> list2=jdbc.queryForList(sql2);
        list.addAll(list2);
        return list;
    }

    @CrossOrigin
    @RequestMapping("/getData/202/realtime/serveravghot")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_serveravg2(){
        String sql="select Equipment,PointName,SiteName from realdata_once where Location='JF202'  and PointName='热通道平均' order by Equipment limit 0,14";
//        String sql2="select Equipment,PointName,SiteName from realtime4 where Location='JF202'  and PointName='热通道下平均' order by Equipment";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        List <Map<String,Object>> list2=jdbc.queryForList(sql2);
//        list.addAll(list2);
        return list;
    }

    @CrossOrigin
    @RequestMapping("/getData/202/realtime/serverpower")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_serverpower(){
        String sql="select * from realdata_once where Location='JF202'  and PointName='服务器功率' order by Equipment";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }

















    @CrossOrigin
    @RequestMapping("/getData/202/realdata/ktnew")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_data(){

        //        kt2
        List <Map<String,Object>> list_all= new ArrayList<>();
        String sql20_sf=" select * from realdata_once where Location='JF202' and Equipment='空调0'";
//        String sql20_sfd=" select Value0,time from realdata_once where Location='JF202' and Equipment='空调0' and PointName='送风温度设定'";
//        String sql20_hf=" select Value0,time from realdata_once where Location='JF202' and Equipment='空调0' and PointName='回风温度1' ";
//        String sql20_hfd=" select Value0,time from realdata_once where Location='JF202' and Equipment='空调0' and PointName='回风温度设定'";
//        String sql20_ysj1=" select Value0,time from realdata_once where Location='JF202' and Equipment='空调0' and PointName='压缩机1容量' ";
//        String sql20_ysj2=" select Value0,time from realdata_once where Location='JF202' and Equipment='空调0' and PointName='压缩机2容量' ";
//        String sql20_fj1=" select Value0,time from realdata_once where Location='JF202' and Equipment='空调0' and PointName='风机1转速'";
//        String sql20_fj2=" select Value0,time from realdata_once where Location='JF202' and Equipment='空调0'  and PointName='风机2转速'";
//        String sql20_p=" select Value0,time from realdata_once where Location='JF202' and Equipment='空调0' and PointName='空调功率'";

        Map<Integer,Object> kt_all = new HashMap<>();
        for(Integer i=1;i<=20;i++){
            String sql_temp=sql20_sf.replace("空调0","空调"+i);
            List <Map<String,Object>> list_kt=jdbc.queryForList(sql_temp); //一台空调所有参数
            Map<String,Object> temp = new HashMap<>();
            for(Map<String,Object> kt:list_kt){
                    Object PointName = kt.get("PointName");
                    String PointName2= PointName.toString();
                    Object Value0 = kt.get("Value0");
                    temp.put(PointName2,Value0);
            }
            kt_all.put(i,temp);
        }
        Map<String,Object> kt_name = new HashMap<>();
        kt_name.put("机房空调",kt_all);
        list_all.add(kt_name);
        return list_all;
    }




    @CrossOrigin
    @RequestMapping("/getData/202/realdata/servernew")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_p(){

        List <Map<String,Object>> list_data= new ArrayList<>();  //储存返回的json
        Map<String, Object> data = new HashMap<String, Object>();

        List<String> server = Arrays.asList("A","B","C","D","E","F","G","H","J","K","L","M","N","P");
        String sql="select Value0 from realdata_once where Location='JF202' and Equipment='服务器A' and SiteName='A1-上' limit 0,1";

        String sql1="select * from realdata_once where Location='JF202' and Equipment='服务器' and SiteName='X1-上' ";
        String sql2="select * from realdata_once where Location='JF202' and Equipment='服务器' and SiteName='X1-下' ";
        List list_value= new ArrayList<>();
        Integer server_num=1;
        List list_risk= new ArrayList<>();
        List list_name= new ArrayList<>();

        List <Map<String,Object>> list1= new ArrayList<>();
        List <Map<String,Object>> list2= new ArrayList<>();
        Map<String, Object> server_temp = new HashMap<String, Object>();  //服务器
        for (String c:server){  //遍历服务器
            String sql_temp1=sql1.replace("'服务器'","'服务器"+c+"'");
            String sql_temp2=sql2.replace("'服务器'","'服务器"+c+"'");

            Map<Integer, Object> server_site1 =new HashMap<Integer, Object>();  //服务器测点
            Map<Integer, Object> server_site2 =new HashMap<Integer, Object>();  //服务器测点

            Map<String, Object> server_site =new HashMap<String, Object>();  //服务器测点
            for(Integer i=1;i<=23;i++){ //遍历测点
                String sql_temp11=sql_temp1.replace("'X1","'"+c+i.toString());    //新建变量，否则只能替换第一次，后面X1变成A1
                String sql_temp22=sql_temp2.replace("'X1","'"+c+i.toString());
                list1=jdbc.queryForList(sql_temp11);
                list2=jdbc.queryForList(sql_temp22);

                String riskStr = new String();
                String sitename = new String();
                Integer num0 =0 ;

                for (Map<String,Object> l:list1){

                    Object temp_value=l.get("Value0");
                    Double risk=Double.parseDouble(temp_value.toString());
                    riskStr = String.format("%.2f", risk);
//                    list_risk.add(Double.valueOf(riskStr));
                    Object temp_name1=l.get("SiteName");
                    sitename=(temp_name1.toString());
                    int stop_point=sitename.indexOf('-');  //截取数字
                    String num=sitename.substring(1,stop_point);
                    num0= new Integer(num);
                }
                server_site1.put(num0,riskStr);


                for (Map<String,Object> l:list2){
                    Object temp_value=l.get("Value0");
                    Double risk=Double.parseDouble(temp_value.toString());
                    riskStr = String.format("%.2f", risk);
//                    list_risk.add(Double.valueOf(riskStr));
                    Object temp_value1=l.get("SiteName");
                    sitename=(temp_value1.toString());
                    int stop_point=sitename.indexOf('-');  //截取数字
                    String num=sitename.substring(1,stop_point);
                    num0= new Integer(num);
                }
                server_site2.put(num0,riskStr);
//                list_data.addAll(list1);
//                list_data.addAll(list2);
//                data.put(sql_temp1,Arrays.asList(c,i));  //debugs
            }
            TreeMap<Integer, Object> server_site11 = new TreeMap<>(server_site1);
            TreeMap<Integer, Object> server_site22 = new TreeMap<>(server_site2);
            server_site.put(c+"上",server_site11);
            server_site.put(c+"下",server_site22);
            server_temp.put(c,server_site);
//            break;
//            server_num+=1;
//            data.put(sql_temp1,c);
        }
//        String sql_temp1=sql1.replace("'服务器'","'服务器"+"B'");  //字符串里有双引号
//        sql_temp1=sql_temp1.replace("'X1","'B"+server_num.toString());   //字符串里有双引号的左半边
//        list1=jdbc.queryForList(sql_temp1);
//        System.out.print(sql_temp1);
//        list_data.add(data);
//        data.put("risk",list_risk);
//        data.put("sitename",list_name);
        data.put("202",server_temp);
        list_data.add(data);
        return list_data;
    }









}

