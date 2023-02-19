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
//public class realdata_203_controller {
//
//    @Autowired
//    private JdbcTemplate jdbc;
//
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/realtime/ktall")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata203_kt(){
//        String sql="select Equipment,PointName,SiteName,Value0 from realdata_once where Location='JF203' and EquipmentType='KT'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/realtime/ktparams")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata203_ktparams(){
//        String sql="select Equipment,SiteName from realdata_once where Location='JF203' and PointName='所有参数' limit 0,20";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/realtime/serverall")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata203_server(){
//        String sql="select Equipment,PointName,SiteName,Value0 from realdata_once where Location='JF203' and EquipmentType='Server'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/realtime/serveravgcold")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata203_serveravg(){
//        String sql="select Equipment,PointName,SiteName from realdata_once where Location='JF203'  and PointName='冷通道上平均' order by Equipment limit 0,14";
//        String sql2="select Equipment,PointName,SiteName from realdata_once where Location='JF203'  and PointName='冷通道下平均' order by Equipment limit 0,14";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        List <Map<String,Object>> list2=jdbc.queryForList(sql2);
//        list.addAll(list2);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/realtime/serveravghot")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata203_serveravg2(){
//        String sql="select Equipment,PointName,SiteName from realdata_once where Location='JF203'  and PointName='热通道平均' order by Equipment limit 0,14";
////        String sql2="select Equipment,PointName,SiteName from realtime4 where Location='JF203'  and PointName='热通道下平均' order by Equipment";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
////        List <Map<String,Object>> list2=jdbc.queryForList(sql2);
////        list.addAll(list2);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/realtime/serverpower")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata203_serverpower(){
//        String sql="select * from realdata_once where Location='JF203'  and PointName='服务器功率' order by Equipment";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/realdata/ktnew")
//    @ResponseBody
//    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata203_data(){
//
//        //        kt2
//        List <Map<String,Object>> list_all= new ArrayList<>();
//        String sql20_sf=" select * from realdata_once where Location='JF203' and Equipment='空调0'";
////        String sql20_sfd=" select Value0,time from realdata_once where Location='JF203' and Equipment='空调0' and PointName='送风温度设定'";
////        String sql20_hf=" select Value0,time from realdata_once where Location='JF203' and Equipment='空调0' and PointName='回风温度1' ";
////        String sql20_hfd=" select Value0,time from realdata_once where Location='JF203' and Equipment='空调0' and PointName='回风温度设定'";
////        String sql20_ysj1=" select Value0,time from realdata_once where Location='JF203' and Equipment='空调0' and PointName='压缩机1容量' ";
////        String sql20_ysj2=" select Value0,time from realdata_once where Location='JF203' and Equipment='空调0' and PointName='压缩机2容量' ";
////        String sql20_fj1=" select Value0,time from realdata_once where Location='JF203' and Equipment='空调0' and PointName='风机1转速'";
////        String sql20_fj2=" select Value0,time from realdata_once where Location='JF203' and Equipment='空调0'  and PointName='风机2转速'";
////        String sql20_p=" select Value0,time from realdata_once where Location='JF203' and Equipment='空调0' and PointName='空调功率'";
//
//        Map<Integer,Object> kt_all = new HashMap<>();
//        for(Integer i=1;i<=20;i++){
//            String sql_temp=sql20_sf.replace("空调0","空调"+i);
//            List <Map<String,Object>> list_kt=jdbc.queryForList(sql_temp); //一台空调所有参数
//            Map<String,Object> temp = new HashMap<>();
//            for(Map<String,Object> kt:list_kt){
//                    Object PointName = kt.get("PointName");
//                    String PointName2= PointName.toString();
//                    Object Value0 = kt.get("Value0");
//                    temp.put(PointName2,Value0);
//            }
//            kt_all.put(i,temp);
//        }
//        Map<String,Object> kt_name = new HashMap<>();
//        kt_name.put("机房空调",kt_all);
//        list_all.add(kt_name);
//        return list_all;
//    }
//
//
//
//
//    @CrossOrigin
//    @RequestMapping("/getData/203/realdata/servernew")
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
//        String sql1="select * from realdata_once where Location='JF203' and Equipment='服务器' and SiteName='X1-上' ";
//        String sql2="select * from realdata_once where Location='JF203' and Equipment='服务器' and SiteName='X1-下' ";
//        List list_value= new ArrayList<>();
//        Integer server_num=1;
//        List list_datavalue= new ArrayList<>();
//        List list_name= new ArrayList<>();
//
//        List <Map<String,Object>> list1= new ArrayList<>();
//        List <Map<String,Object>> list2= new ArrayList<>();
//        List <Map<String,Object>> list3= new ArrayList<>();
//        List <Map<String,Object>> list4= new ArrayList<>();
//        Map<String, Object> server_temp = new HashMap<String, Object>();  //服务器冷通道
//        Map<String, Object> server_temp1 = new HashMap<String, Object>();  //服务器热通道
//        Map<String, Object> server_temp2 = new HashMap<String, Object>();  //服务器功率
//
//        for (String c:server){  //遍历服务器
//            String sql_temp1=sql1.replace("'服务器'","'服务器"+c+"'");
//            String sql_temp2=sql2.replace("'服务器'","'服务器"+c+"'");
//
//
//            Map<Integer, Object> server_site1 =new HashMap<Integer, Object>();  //服务器测点
//            Map<Integer, Object> server_site2 =new HashMap<Integer, Object>();  //服务器测点
//            Map<Integer, Object> server_site3 =new HashMap<Integer, Object>();  //服务器测点热通道
//
//            Map<String, Object> server_site =new HashMap<String, Object>();  //服务器测点
//            Integer siteNum=23;
//            Double temperature1=0.0;
//            Double temperature2=0.0;
//            Double temperature3=0.0;
//
//            //遍历并修改测点
//            Integer cntNull1=0;//每组服务器置零
//            Integer cntNull2=0;
//            Integer cntNull3=0;
//            for(Integer i=1;i<=siteNum;i++){ //遍历测点
//                String sql_temp11=sql_temp1.replace("'X1","'"+c+i.toString());    //新建变量，否则只能替换第一次，后面X1变成A1
//                String sql_temp22=sql_temp2.replace("'X1","'"+c+i.toString());  //冷通道
//
//                String sql_temp33=sql_temp1.replace("X1-上",i.toString());           //热通道 ，上测点X1-上变为1
////                String sql_temp44=sql_temp2.replace("X1-下",i.toString());
//
//                list1=jdbc.queryForList(sql_temp11);  //查询的每一条冷通道测点
//                list2=jdbc.queryForList(sql_temp22);
//                list3=jdbc.queryForList(sql_temp33);
////                list4=jdbc.queryForList(sql_temp44);
//
//                String datavalueStr = new String();
//                String sitename = new String();
//                Integer num0 =0 ;
//
////                temperature1=0.0;
//                for (Map<String,Object> l:list1){//冷测点取值,上
//
//                    Object temp_value=l.get("Value0");
//                    Double datavalue=Double.parseDouble(temp_value.toString());
//                    datavalueStr = String.format("%.2f", datavalue);
//                    if (Double.parseDouble(datavalueStr)==0.0){ cntNull1=cntNull1+1;}//统计测点为0的个数
//                    temperature1+=Double.parseDouble(datavalueStr);//A所有上半测点求和求平均
////                    list_datavalue.add(Double.valueOf(datavalueStr));
//                    Object temp_name1=l.get("SiteName");
//                    sitename=(temp_name1.toString());
//                    int stop_point=sitename.indexOf('-');  //截取数字
//                    String num=sitename.substring(1,stop_point);
//                    num0= new Integer(num);
//                }
////                server_site1_avg.put("Atop",temperature_all/siteNum)
//                server_site1.put(num0,datavalueStr);
//
//
//                for (Map<String,Object> l:list2){////测点取值,下
//                    Object temp_value=l.get("Value0");
//                    Double datavalue=Double.parseDouble(temp_value.toString());
//                    datavalueStr = String.format("%.2f", datavalue);
//                    if (Double.parseDouble(datavalueStr)==0.0){ cntNull2=cntNull2+1;}
////                    list_datavalue.add(Double.valueOf(datavalueStr));
//                    temperature2+=Double.parseDouble(datavalueStr);//A所有下半测点求和求平均
//
//                    Object temp_value1=l.get("SiteName");
//                    sitename=(temp_value1.toString());
//                    int stop_point=sitename.indexOf('-');  //截取数字
//                    String num=sitename.substring(1,stop_point);
//                    num0= new Integer(num);
//                }
//                server_site2.put(num0,datavalueStr);
////                list_data.addAll(list1);
////                list_data.addAll(list2);
////                data.put(sql_temp1,Arrays.asList(c,i));  //debugs
//
//
//
//
//                for (Map<String,Object> l:list3){//热测点取值所有测点
//
//                    Object temp_value=l.get("Value0");
//                    Double datavalue=Double.parseDouble(temp_value.toString());
//                    datavalueStr = String.format("%.2f", datavalue);
//                    if (Double.parseDouble(datavalueStr)==0.0){ cntNull3=cntNull3+1;}
//
//                    temperature3+=Double.parseDouble(datavalueStr);//A所有热通道求和求平均
////                    list_datavalue.add(Double.valueOf(datavalueStr));
//                    Object temp_name1=l.get("SiteName");
//                    sitename=(temp_name1.toString());
////                    int stop_point=sitename.indexOf('-');  //截取数字
////                    String num=sitename.substring(1,stop_point);
//                    num0= new Integer(sitename);
//                }
////                server_site1_avg.put("Atop",temperature_all/siteNum)
//                server_site3.put(num0,datavalueStr);
//
//            }
//            temperature2=temperature2/(siteNum-cntNull1);
//            temperature1=temperature1/(siteNum-cntNull2);
//            temperature3=temperature3/(siteNum-cntNull3);
//            String t1 = String.format("%.2f", temperature1);
//            String t2 = String.format("%.2f", temperature2);
//            String t3 = String.format("%.2f", temperature3);
//
//
//            Map<String, Object> site_avg = new TreeMap<>();
//            Map<String, Object> site_avg1 = new TreeMap<>();//热通道平均
//            Map<String, Object> site_name = new TreeMap<>(); //冷通道
//            Map<String, Object> site_name1 = new TreeMap<>();//热通道
//            Map<String, Object> site_name2 = new TreeMap<>();//功率
//            TreeMap<String, Object> server_site0 = new TreeMap<>(server_site);
//            TreeMap<Integer, Object> server_site11 = new TreeMap<>(server_site1);//冷通道上
//            TreeMap<Integer, Object> server_site22 = new TreeMap<>(server_site2); //冷通道下
//            TreeMap<Integer, Object> server_site33 = new TreeMap<>(server_site3);//热通道
////            TreeMap<String, Object> server_site11_avg = new TreeMap<>(server_site1_avg);
////            TreeMap<String, Object> server_site22_avg = new TreeMap<>(server_site2_avg);  //测点排序
//
//            site_avg.put("upall",t1);//冷通道上
//            site_avg.put("downall",t2);//冷通道下
//            site_avg1.put("all",t3); ////热通道
//
//            server_site0.put("up",server_site11);
//            server_site0.put("down",server_site22);
//
//            site_name.put("avg",site_avg);
//            site_name.put("sitedetail",server_site0);
//
//            //热通道
//            site_name1.put("sitedetail",server_site33);
//            site_name1.put("avg",site_avg1);
//
//
//            server_temp.put(c,site_name);
//            server_temp1.put(c,site_name1);
////            break;
////            server_num+=1;
////            data.put(sql_temp1,c);
//        }
////        String sql_temp1=sql1.replace("'服务器'","'服务器"+"B'");  //字符串里有双引号
////        sql_temp1=sql_temp1.replace("'X1","'B"+server_num.toString());   //字符串里有双引号的左半边
////        list1=jdbc.queryForList(sql_temp1);
////        System.out.print(sql_temp1);
////        list_data.add(data);
////        data.put("datavalue",list_datavalue);
////        data.put("sitename",list_name);
//        String sql_p="select * from realdata_once where Location='JF203' and PointName='服务器功率' ";
//        List <Map<String,Object>> list_p=jdbc.queryForList(sql_p);
//        List <Map<String,Object>>temp_p= new ArrayList<>();
//        TreeMap<String,Object> temp= new TreeMap<>();
//        for(Map<String,Object> p:list_p){
//            Object p_name=p.get("Equipment");
//            Object p_value=p.get("Value0");
//            temp.put(p_name.toString().substring(3),p_value);
//            temp_p.add(temp);
//        }
////        server_temp2=temp_p;
//        data.put("servercold",server_temp);
//        data.put("serverhot",server_temp1);
//        data.put("serverpower",temp);
//
//        list_data.add(data);
//        return list_data;
//    }
//
//
//
//
//
//
//
//
//
//}
//
