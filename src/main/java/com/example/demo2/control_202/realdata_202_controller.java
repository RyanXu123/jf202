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
public class realdata_202_controller {

    @Autowired
    private JdbcTemplate jdbc;

///之前的代码
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
















////新代码
    @CrossOrigin
    @RequestMapping("/getData/202/realdata/ktnew")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_data(){

        //        kt2
        List <Map<String,Object>> list_all= new ArrayList<>();
        String sql20_sf=" select * from realdata_once where Location='JF202' and Equipment='空调0'";  //从表中筛选某空调的所有参数
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
            String sql_temp=sql20_sf.replace("空调0","空调"+i);   //遍历所有空调 1，2....20

            List <Map<String,Object>> list_kt=jdbc.queryForList(sql_temp); //一台空调所有参数，list里面为一台空调的所有参数：回风温度、送风温度等等
            //select * from realdata_once where Location='JF202' and Equipment='空调1'  在数据库里面运行该sql，查看返回的字段
            Map<String,Object> temp = new HashMap<>();

            for(Map<String,Object> kt:list_kt){//遍历返回的list，截取PointName和value0列，即参数名称和参数值
                    Object PointName = kt.get("PointName");
                    String PointName2= PointName.toString();
                    Object Value0 = kt.get("Value0");
                    temp.put(PointName2,Value0);
            }
            kt_all.put(i,temp);  // 存入键值对，如空调1 为（1，空调1所有参数的PointName和value0对）
        }
        Map<String,Object> kt_name = new HashMap<>();
        kt_name.put("机房空调",kt_all);   // 存入键值对 ,样式为（机房空调，{[1，空调1参数键值对],[2,空调2参数键值对]...})）
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
        List list_datavalue= new ArrayList<>();
        List list_name= new ArrayList<>();

        List <Map<String,Object>> list1= new ArrayList<>();
        List <Map<String,Object>> list2= new ArrayList<>();
        List <Map<String,Object>> list3= new ArrayList<>();
        List <Map<String,Object>> list4= new ArrayList<>();
        Map<String, Object> server_temp = new HashMap<String, Object>();  //服务器冷通道
        Map<String, Object> server_temp1 = new HashMap<String, Object>();  //服务器热通道
        Map<String, Object> server_temp2 = new HashMap<String, Object>();  //服务器功率

        for (String c:server){  //遍历服务器 c为（"A","B","C","D" ...）
            String sql_temp1=sql1.replace("'服务器'","'服务器"+c+"'");
            String sql_temp2=sql2.replace("'服务器'","'服务器"+c+"'");


            Map<Integer, Object> server_site1 =new HashMap<Integer, Object>();  //某列服务器冷通道上测点
            Map<Integer, Object> server_site2 =new HashMap<Integer, Object>();  //某列服务器冷通道下测点
            Map<Integer, Object> server_site3 =new HashMap<Integer, Object>();  //某列服务器热通道测点

            Map<String, Object> server_site =new HashMap<String, Object>();  //服务器测点
            Integer siteNum=23;
            Double temperature1=0.0;  //某列服务器冷通道上测点温度求和
            Double temperature2=0.0;  //某列服务器冷通道下测点温度求和
            Double temperature3=0.0;  //某列服务器热通道测点温度求和

            //遍历并修改测点
            Integer cntNull1=0;//每组服务器冷通道上非0测点计数
            Integer cntNull2=0;//每组服务器冷通道下非0测点计数
            Integer cntNull3=0;//每组服务器热通道非0测点计数
            for(Integer i=1;i<=siteNum;i++){ //遍历测点
                String sql_temp11=sql_temp1.replace("'X1","'"+c+i.toString());    //新建变量，否则只能替换第一次，后面X1变成A1
                String sql_temp22=sql_temp2.replace("'X1","'"+c+i.toString());  //遍历每组服务器的冷通道的每一个侧测点
                //select Value0 from realdata_once where Location='JF202' and Equipment='服务器A' and SiteName='A1-上'
                String sql_temp33=sql_temp1.replace("X1-上",i.toString());           //热通道 ，上测点X1-上变为1
//                String sql_temp44=sql_temp2.replace("X1-下",i.toString());

                list1=jdbc.queryForList(sql_temp11); //查询的某服务器的一条冷通道上测点
                list2=jdbc.queryForList(sql_temp22);//查询的某服务器的一条冷通道下测点
                list3=jdbc.queryForList(sql_temp33);//查询的某服务器的一条热通道测点
//                list4=jdbc.queryForList(sql_temp44);

                String datavalueStr = new String();
                String sitename = new String();
                Integer num0 =0 ;

//                temperature1=0.0;
                for (Map<String,Object> l:list1){//对某服务器某测点的字段取值，去其value0

                    Object temp_value=l.get("Value0"); //取得值
                    Double datavalue=Double.parseDouble(temp_value.toString());  //变为浮点型
                    datavalueStr = String.format("%.2f", datavalue); //保留两位小数

                    if (Double.parseDouble(datavalueStr)==0.0){ //统计该上测点是否为零
                        cntNull1=cntNull1+1;
                    }

                    temperature1+=Double.parseDouble(datavalueStr);//将该测点加总到某列服务器上测点总和上去
//                    list_datavalue.add(Double.valueOf(datavalueStr));
                    Object temp_name1=l.get("SiteName"); //获取该测点的名字
                    sitename=(temp_name1.toString()); //
                    int stop_point=sitename.indexOf('-');  //只保留数字部分
                    String num=sitename.substring(1,stop_point);
                    num0= new Integer(num);//并变为整数
                }
//                server_site1_avg.put("Atop",temperature_all/siteNum)
                server_site1.put(num0,datavalueStr); //将该测点的名字与数值生成键值对 （A01-上，22.5）=》》（1，22.5）


                for (Map<String,Object> l:list2){////测点取值,下
                    Object temp_value=l.get("Value0");
                    Double datavalue=Double.parseDouble(temp_value.toString());
                    datavalueStr = String.format("%.2f", datavalue);
                    if (Double.parseDouble(datavalueStr)==0.0){ cntNull2=cntNull2+1;}
//                    list_datavalue.add(Double.valueOf(datavalueStr));
                    temperature2+=Double.parseDouble(datavalueStr);//A所有下半测点加总

                    Object temp_value1=l.get("SiteName");
                    sitename=(temp_value1.toString());
                    int stop_point=sitename.indexOf('-');  //截取数字
                    String num=sitename.substring(1,stop_point);
                    num0= new Integer(num);
                }
                server_site2.put(num0,datavalueStr);//将该测点的名字与数值生成键值对 （A01-下，22.5）=》》（1，22.5）
//                list_data.addAll(list1);
//                list_data.addAll(list2);
//                data.put(sql_temp1,Arrays.asList(c,i));  //debugs




                for (Map<String,Object> l:list3){//热测点取值所有测点

                    Object temp_value=l.get("Value0");
                    Double datavalue=Double.parseDouble(temp_value.toString());
                    datavalueStr = String.format("%.2f", datavalue);
                    if (Double.parseDouble(datavalueStr)==0.0){ cntNull3=cntNull3+1;}

                    temperature3+=Double.parseDouble(datavalueStr);//A所有热通道求和求平均
//                    list_datavalue.add(Double.valueOf(datavalueStr));
                    Object temp_name1=l.get("SiteName");
                    sitename=(temp_name1.toString());
//                    int stop_point=sitename.indexOf('-');  //截取数字省略，本身sitename为数值
//                    String num=sitename.substring(1,stop_point);
                    num0= new Integer(sitename);
                }
//                server_site1_avg.put("Atop",temperature_all/siteNum)
                server_site3.put(num0,datavalueStr);//将该测点的名字与数值生成键值对 （01，22.5）=》》（1，22.5）


            }
            temperature2=temperature2/(siteNum-cntNull1);//某列服务冷通道上测点的总和除非0测点的个数，为某列服务器冷通道上测点平均
            temperature1=temperature1/(siteNum-cntNull2);//某列服务冷通道下测点的总和除非0测点的个数，为某列服务器冷通道下测点平均
            temperature3=temperature3/(siteNum-cntNull3);//某列服务热通道测点的总和除非0测点的个数，为某列服务器热通道测点平均

            String t1 = String.format("%.2f", temperature1);
            String t2 = String.format("%.2f", temperature2);
            String t3 = String.format("%.2f", temperature3); // 保留两位小数


            Map<String, Object> site_avg = new TreeMap<>();//某服务器冷通道平均
            Map<String, Object> site_avg1 = new TreeMap<>();//热通道平均
            Map<String, Object> site_name = new TreeMap<>(); //冷通道
            Map<String, Object> site_name1 = new TreeMap<>();//热通道
            Map<String, Object> site_name2 = new TreeMap<>();//功率
            TreeMap<String, Object> server_site0 = new TreeMap<>(server_site);
            TreeMap<Integer, Object> server_site11 = new TreeMap<>(server_site1);//冷通道上 排序
            TreeMap<Integer, Object> server_site22 = new TreeMap<>(server_site2); //冷通道下 排序
            TreeMap<Integer, Object> server_site33 = new TreeMap<>(server_site3);//热通道 排序
//            TreeMap<String, Object> server_site11_avg = new TreeMap<>(server_site1_avg);
//            TreeMap<String, Object> server_site22_avg = new TreeMap<>(server_site2_avg);  //测点排序

            site_avg.put("upall",t1);//冷通道上，（upall，某列冷上平均）
            site_avg.put("downall",t2);//冷通道下 （downall，某列冷下平均）
            site_avg1.put("all",t3); ////热通道  （all，某列热平均）

            server_site0.put("up",server_site11); //某列服务器所有上测点  （up，{服务器所有测点（1，22）（2，22）..}）
            server_site0.put("down",server_site22);//某列服务器所有下测点  （down，{服务器所有测点（1，22）（2，22）..}）

            site_name.put("avg",site_avg);  // (avg, {（upall，某列冷上平均）,（downall，某列冷下平均）})
            site_name.put("sitedetail",server_site0);//(sitedetail,{（up，{服务器所有测点（1，22）（2，22）..}）,（down，{服务器所有测点（1，22）（2，22）..}）})

            //热通道
            site_name1.put("sitedetail",server_site33); //(sitedetail,{服务器所有测点（1，22）（2，22）..})
            site_name1.put("avg",site_avg1); //(avg,（all，某列热平均）)


            server_temp.put(c,site_name); //冷通道（A，{(avg,xx),(sitedetail,xx)}）
            server_temp1.put(c,site_name1); //热通道（A，{}）
//            break;
//            server_num+=1;
//            data.put(sql_temp1,c);
        }
//        String sql_temp1=sql1.replace("'服务器'","'服务器"+"B'");  //字符串里有双引号
//        sql_temp1=sql_temp1.replace("'X1","'B"+server_num.toString());   //字符串里有双引号的左半边
//        list1=jdbc.queryForList(sql_temp1);
//        System.out.print(sql_temp1);
//        list_data.add(data);
//        data.put("datavalue",list_datavalue);
//        data.put("sitename",list_name);
        String sql_p="select * from realdata_once where Location='JF202' and PointName='服务器功率' ";
        List <Map<String,Object>> list_p=jdbc.queryForList(sql_p);
        List <Map<String,Object>>temp_p= new ArrayList<>();
        TreeMap<String,Object> temp= new TreeMap<>();
        for(Map<String,Object> p:list_p){
            Object p_name=p.get("Equipment");
            Object p_value=p.get("Value0");
            temp.put(p_name.toString().substring(3),p_value);
            temp_p.add(temp);
        }
//        server_temp2=temp_p;
        data.put("servercold",server_temp);
        data.put("serverhot",server_temp1);
        data.put("serverpower",temp);

        list_data.add(data);
        return list_data;
    }






    ////新代码
    @CrossOrigin
    @RequestMapping("/getData/202/realdata/kt_diagnosis")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_judge(){

        //        kt2
        List <Map<String,Object>> list_all= new ArrayList<>();
        String sql20_sf=" select PointName,Equipment,Value0 from realdata_once where Location='JF202' and Equipment='空调0'";  //从表中筛选某空调的所有参数
        List <Map<String,Object>> list_temp= new ArrayList<>();
        Map<String,Object> a = new HashMap<>();
        Map<Integer,Object> kt_all = new HashMap<>();
        for(Integer i=1;i<=20;i++){
            String sql_temp=sql20_sf.replace("空调0","空调"+i);   //遍历所有空调 1，2....20
            List <Map<String,Object>> list_kt=jdbc.queryForList(sql_temp); //一台空调所有参数，list里面为一台空调的所有参数：回风温度、送风温度等等

            Map<String,Object> temp = new HashMap<>();
            for (Map<String,Object> c:list_kt){
                String params=c.get("PointName").toString();
                Double value0=(double) c.get("Value0");
                if(params.equals("送风温度1") | params.equals("送风温度4")){
                    if(value0<22 | value0>26){
                        list_temp.add(c);
                    }
                }else if(params.equals("回风温度4")|params.equals("回风温度3")|params.equals("回风温度2")|params.equals("回风温度1")){
                    if(value0<32 | value0>38){
                        list_temp.add(c);
                    }
                }else if(params.equals("压缩机1容量")|params.equals("压缩机2容量")){
                    if(value0<33 | value0>100){
                        list_temp.add(c);
                    }
                }else if(params.equals("风机1转速")|params.equals("风机2转速")){
                    if(value0<33 | value0>100){
                        list_temp.add(c);
                    }
                }
            }
        }

    return list_temp;
    }






    @CrossOrigin
    @RequestMapping("/getData/202/realdata/server_diagnosis")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_judge2(){

        //        kt2
        List <Map<String,Object>> list_all= new ArrayList<>();
        List<String> server = Arrays.asList("A","B","C","D","E","F","G","H","J","K","L","M","N","P");
        String sql20_sf=" select PointName,Equipment,Value0,SiteName from realdata_once where Location='JF202' and Equipment='服务器'";  //从表中筛选某空调的所有参数
        List <Map<String,Object>> list_temp= new ArrayList<>();
        Map<String,Object> a = new HashMap<>();
        Map<Integer,Object> kt_all = new HashMap<>();
        for(String c:server){
            String sql_temp=sql20_sf.replace("服务器","服务器"+c);   //遍历所有空调 1，2....20
            List <Map<String,Object>> list_kt=jdbc.queryForList(sql_temp); //一台空调所有参数，list里面为一台空调的所有参数：回风温度、送风温度等等
            Map<String,Object> temp = new HashMap<>();

            for (Map<String,Object> site:list_kt){
                String PointName=site.get("PointName").toString();
                String Equipment=site.get("Equipment").toString();
                String SiteName=site.get("SiteName").toString();
//                String params=site.get("PointName").toString();
                Double value0=(double) site.get("Value0");
                if(PointName.equals("冷通道温度")){
                    if(value0>26.8){
                        list_temp.add(site);
                    }
                }else if(PointName.equals("热通道温度")){
                    if(value0<28 | value0>32){
                        list_temp.add(site);
                    }
                }
            }
        }

        return list_temp;
    }


}

