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
//import java.util.*;
//
//@Controller
//public class riskdata_203_controller {
//
//    @Autowired
//    private JdbcTemplate jdbc;
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/riskdatanew")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata203_p(){
//
//        List <Map<String,Object>> list_data= new ArrayList<>();  //储存返回的json
//        Map<String, Object> data = new HashMap<String, Object>();
//
//        List<String> server = Arrays.asList("A","B","C","D","E","F","G","H","J","K","L","M","N","P");
//        String sql="select Value0 from realdata_once where Location='JF203' and Equipment='服务器A' and SiteName='A1-上' limit 0,1";
//
////        String sql1="select * from realdata_once where Location='JF203' and Equipment='服务器' and SiteName='X1-上' ";
////        String sql2="select * from realdata_once where Location='JF203' and Equipment='服务器' and SiteName='X1-下' ";
////        List list_value= new ArrayList<>();
////        Integer server_num=1;
////        List list_risk= new ArrayList<>();
////        List list_name= new ArrayList<>();
////
////        List <Map<String,Object>> list1= new ArrayList<>();
////        List <Map<String,Object>> list2= new ArrayList<>();
////        Map<String, Object> server_temp = new HashMap<String, Object>();  //服务器
////        for (String c:server){  //遍历服务器
////            String sql_temp1=sql1.replace("'服务器'","'服务器"+c+"'");
////            String sql_temp2=sql2.replace("'服务器'","'服务器"+c+"'");
////
////            Map<String, Object> server_site1 =new HashMap<String, Object>();  //服务器测点
////            Map<String, Object> server_site2 =new HashMap<String, Object>();  //服务器测点
////            Map<String, Object> server_site =new HashMap<String, Object>();  //服务器测点
////            for(Integer i=1;i<=23;i++){ //遍历测点
////                String sql_temp11=sql_temp1.replace("'X1","'"+c+i.toString());    //新建变量，否则只能替换第一次，后面X1变成A1
////                String sql_temp22=sql_temp2.replace("'X1","'"+c+i.toString());
////                list1=jdbc.queryForList(sql_temp11);
////                list2=jdbc.queryForList(sql_temp22);
////
////                String riskStr = new String();
////                String sitename = new String();
////
////                for (Map<String,Object> l:list1){
////                    Object temp_value=l.get("Value0");
////                    Double risk=Double.parseDouble(temp_value.toString());
////                    risk=risk/42;
////                    if(risk == 0) { risk = -1.0;}
////                    riskStr = String.format("%.2f", risk);
//////                    list_risk.add(Double.valueOf(riskStr));
////                    Object temp_value1=l.get("SiteName");
////                    sitename=(temp_value1.toString());
////                }
////                server_site1.put(sitename,riskStr);
////
////                for(Map<String,Object> n:list2){
////                    Object temp_value=n.get("Value0");
////                    Double risk=Double.parseDouble(temp_value.toString());
////                    risk=risk/42;
////                    if(risk == 0) { risk = -1.0;}
////                    riskStr = String.format("%.2f", risk);
//////                    list_risk.add(Double.valueOf(riskStr));
////                    Object temp_value1=n.get("SiteName");
////                    sitename=(temp_value1.toString());
////                }
////                server_site2.put(sitename,riskStr);
//////                list_data.addAll(list1);
//////                list_data.addAll(list2);
//////                data.put(sql_temp1,Arrays.asList(c,i));  //debugs
////            }
////            server_site.put(c+"上",server_site1);
////            server_site.put(c+"下",server_site2);
////            server_temp.put(c,server_site);
//////            break;
//////            server_num+=1;
//////            data.put(sql_temp1,c);
////        }
//
//
//        String sql1="select * from realdata_once where Location='JF203' and Equipment='服务器' and SiteName='X1-上' ";
//        String sql2="select * from realdata_once where Location='JF203' and Equipment='服务器' and SiteName='X1-下' ";
//        List list_value= new ArrayList<>();
//        Integer server_num=1;
//        List list_risk= new ArrayList<>();
//        List list_name= new ArrayList<>();
//
//        List <Map<String,Object>> list1= new ArrayList<>();
//        List <Map<String,Object>> list2= new ArrayList<>();
//        Map<String, Object> server_temp = new HashMap<String, Object>();  //服务器
//        for (String c:server){  //遍历服务器
//            String sql_temp1=sql1.replace("'服务器'","'服务器"+c+"'");
//            String sql_temp2=sql2.replace("'服务器'","'服务器"+c+"'");
//
//            Map<Integer, Object> server_site1 =new HashMap<Integer, Object>();  //服务器测点
//            Map<Integer, Object> server_site2 =new HashMap<Integer, Object>();  //服务器测点
//
//            Map<String, Object> server_site =new HashMap<String, Object>();  //服务器测点
//            for(Integer i=1;i<=23;i++){ //遍历测点
//                String sql_temp11=sql_temp1.replace("'X1","'"+c+i.toString());    //新建变量，否则只能替换第一次，后面X1变成A1
//                String sql_temp22=sql_temp2.replace("'X1","'"+c+i.toString());
//                list1=jdbc.queryForList(sql_temp11);
//                list2=jdbc.queryForList(sql_temp22);
//
//                String riskStr = new String();
//                String sitename = new String();
//                Integer num0 =0 ;
//
//                for (Map<String,Object> l:list1){
//
//                    Object temp_value=l.get("Value0");
//                    Double risk=Double.parseDouble(temp_value.toString());
//                    risk=(risk/46);
//                    if(risk == 0) { risk = -1.0;}
//                    riskStr = String.format("%.2f", risk);
//
////                    list_risk.add(Double.valueOf(riskStr));
//                    Object temp_name1=l.get("SiteName");
//                    sitename=(temp_name1.toString());
//                    int stop_point=sitename.indexOf('-');  //截取数字
//                    String num=sitename.substring(1,stop_point);
//                    num0= new Integer(num);
//                }
//                server_site1.put(num0,riskStr);
//
//
//                for (Map<String,Object> l:list2){
//                    Object temp_value=l.get("Value0");
//                    Double risk=Double.parseDouble(temp_value.toString());
//                    risk=(risk/46);
//                    if(risk == 0) { risk = -1.0;}
//                    riskStr = String.format("%.2f", risk);
////                    list_risk.add(Double.valueOf(riskStr));
//                    Object temp_value1=l.get("SiteName");
//                    sitename=(temp_value1.toString());
//                    int stop_point=sitename.indexOf('-');  //截取数字
//                    String num=sitename.substring(1,stop_point);
//                    num0= new Integer(num);
//                }
//                server_site2.put(num0,riskStr);
////                list_data.addAll(list1);
////                list_data.addAll(list2);
////                data.put(sql_temp1,Arrays.asList(c,i));  //debugs
//            }
//            TreeMap<Integer, Object> server_site11 = new TreeMap<>(server_site1);
//            TreeMap<Integer, Object> server_site22 = new TreeMap<>(server_site2);
//            server_site.put(c+"上",server_site11);
//            server_site.put(c+"下",server_site22);
//            server_temp.put(c,server_site);
////            break;
////            server_num+=1;
////            data.put(sql_temp1,c);
//        }
////        String sql_temp1=sql1.replace("'服务器'","'服务器"+"B'");  //字符串里有双引号
////        sql_temp1=sql_temp1.replace("'X1","'B"+server_num.toString());   //字符串里有双引号的左半边
////        list1=jdbc.queryForList(sql_temp1);
////        System.out.print(sql_temp1);
////        list_data.add(data);
////        data.put("risk",list_risk);
////        data.put("sitename",list_name);
//        data.put("203",server_temp);
//        list_data.add(data);
//        return list_data;
//    }
//
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/riskdata")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata203_p1(){
//
//        List <Map<String,Object>> list_data= new ArrayList<>();  //储存返回的json
//        Map<String, Object> data = new HashMap<String, Object>();
//
////        List<String> server = Arrays.asList("A","B","C","D","E","F","G","H","J","K","L","M","N","P");
//        String sql="select * from riskdata where Location='JF203' ";
//
////        String sql1="select * from realdata_once where Location='JF203' and Equipment='服务器' and SiteName='X1-上' ";
////        String sql2="select * from realdata_once where Location='JF203' and Equipment='服务器' and SiteName='X1-下' ";
////        List list_value= new ArrayList<>();
////        Integer server_num=1;
////        List list_risk= new ArrayList<>();
////        List list_name= new ArrayList<>();
//
//        List <Map<String,Object>> list1= jdbc.queryForList(sql);
////        List <Map<String,Object>> list2= new ArrayList<>();
////        data.put("sitename",list_name);
////        list_data.add(data);
//        return list1;
//    }
//}
