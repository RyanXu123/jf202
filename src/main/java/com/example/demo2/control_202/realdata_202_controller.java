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
//    @CrossOrigin
//    @RequestMapping("/getData/202/realtime/ktall")
//    @ResponseBody
////    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata202_kt(){
//        String sql="select Equipment,PointName,SiteName,Value0 from realdata_once where Location='JF202' and EquipmentType='KT'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }

//    @CrossOrigin
//    @RequestMapping("/getData/202/realtime/ktparams")
//    @ResponseBody
////    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata202_ktparams(){
//        String sql="select Equipment,SiteName from realdata_once where Location='JF202' and PointName='所有参数' limit 0,20";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }

//    @CrossOrigin
//    @RequestMapping("/getData/202/realtime/serverall")
//    @ResponseBody
////    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata202_server(){
//        String sql="select Equipment,PointName,SiteName,Value0 from realdata_once where Location='JF202' and EquipmentType='Server'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }

//    @CrossOrigin
//    @RequestMapping("/getData/202/realtime/serveravgcold")
//    @ResponseBody
////    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata202_serveravg(){
//        String sql="select Equipment,PointName,SiteName from realdata_once where Location='JF202'  and PointName='冷通道上平均' order by Equipment limit 0,14";
//        String sql2="select Equipment,PointName,SiteName from realdata_once where Location='JF202'  and PointName='冷通道下平均' order by Equipment limit 0,14";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        List <Map<String,Object>> list2=jdbc.queryForList(sql2);
//        list.addAll(list2);
//        return list;
//    }

//    @CrossOrigin
//    @RequestMapping("/getData/202/realtime/serveravghot")
//    @ResponseBody
////    @Scheduled(fixedRate = 30000)
//    public List<Map<String,Object>> getdata202_serveravg2(){
//        String sql="select Equipment,PointName,SiteName from realdata_once where Location='JF202'  and PointName='热通道平均' order by Equipment limit 0,14";
////        String sql2="select Equipment,PointName,SiteName from realtime4 where Location='JF202'  and PointName='热通道下平均' order by Equipment";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
////        List <Map<String,Object>> list2=jdbc.queryForList(sql2);
////        list.addAll(list2);
//        return list;
//    }

    @CrossOrigin
    @RequestMapping("/getData/202/realtime/serverpower")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_serverpower(){
        String sql="select * from realdata_once where Location='JF202'  and PointName='服务器功率' order by Equipment";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }






    Map<Integer,List<Double>> kt_hf = new HashMap<>();
    Map<Integer,List<Double>> kt_sf = new HashMap<>();

    @CrossOrigin
    @RequestMapping("/getData/202/realdata/kt_stable")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_judge3(){

        List <Map<String,Object>> list_all= new ArrayList<>();
        String sql20_sf=" select PointName,Equipment,Value0 from realdata_once where Location='JF202' and Equipment='空调0'";  //从表中筛选某空调的所有参数
        List <Map<String,Object>> list_temp= new ArrayList<>();
        Map<String,Object> a = new HashMap<>();
        Map<Integer,Object> kt_all = new HashMap<>();
        for(Integer i=1;i<=20;i++){
            String sql_temp=sql20_sf.replace("空调0","空调"+i);   //遍历所有空调 1，2....20
            List <Map<String,Object>> list_kt=jdbc.queryForList(sql_temp); //一台空调所有参数，list里面为一台空调的所有参数：回风温度、送风温度等等

            List<Double> sf =kt_sf.get(i+1);
            List<Double> hf= kt_hf.get(i+1);//第一次进入没有该键值对
            try{
                if(sf.size()==1000){
                    sf.remove(0);
                }
            }catch (Exception e){
                sf=new ArrayList<>();
            }

            try{
                if(hf.size()==1000){
                    hf.remove(0);
                }
            }catch (Exception e){
                hf=new ArrayList<>();
            }

            Map<String,Object> temp = new HashMap<>();
            for (Map<String,Object> c:list_kt){
                String params=c.get("PointName").toString();
                Double value0=(double) c.get("Value0");
                Double value_temp=sf.stream().reduce(Double::sum).orElse(0.0);
                if(params.equals("送风温度1")){
                    if ((value0> value_temp+1|value0<value_temp-1) &value_temp!=0.0){//数据非异常、非空，则进行判断
                        temp.put("kt","空调"+i+1);
                        temp.put("detail"+i+1,"送风波动");
                        list_temp.add(temp);
                        temp.clear();
                    }
                    sf.add(value_temp);
                }else if(params.equals("回风温度1")) {
                    String params2 = c.get("PointName").toString();
                    Double value02 = (double) c.get("Value0");
                    Double value_temp2 = hf.stream().reduce(Double::sum).orElse(0.0);
                    if (params2.equals("回风温度1")) {//数据非异常、非空

                        if ((value02 > value_temp2 + 1 | value02 < value_temp2 - 1) & value_temp2 != 0.0) {
                            temp.put("kt", "空调" + i + 1);
                            temp.put("detail" + i + 1, "回风波动");
                            list_temp.add(temp);
                            temp.clear();
                        }
                        hf.add(value02);
                    }
                }

            }
        }
        return list_temp;
    }









////新代码
    @CrossOrigin
    @RequestMapping("/getData/202/realdata/ktnew")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
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

        TreeMap<Integer,Object> kt_all = new TreeMap<>();
        for(Integer i=1;i<=20;i++){
            String sql_temp=sql20_sf.replace("空调0","空调"+i);   //遍历所有空调 1，2....20

            List <Map<String,Object>> list_kt=jdbc.queryForList(sql_temp); //一台空调所有参数，list里面为一台空调的所有参数：回风温度、送风温度等等
            //select * from realdata_once where Location='JF202' and Equipment='空调1'  在数据库里面运行该sql，查看返回的字段
            LinkedHashMap<String,Object> temp = new LinkedHashMap<>();
            LinkedHashMap<String,Object> temp2 = new LinkedHashMap<>();

            for(Map<String,Object> kt:list_kt){//遍历返回的list，截取PointName和value0列，即参数名称和参数值
                    Object PointName = kt.get("PointName");
                    String PointName2= PointName.toString();
                    Object Value0 = kt.get("Value0");
                    temp.put(PointName2,Value0);
            }
              // 存入键值对，如空调1 为（1，空调1所有参数的PointName和value0对）
            temp2.put("压缩机1容量",temp.get("压缩机1容量"));
            temp2.put("压缩机2容量",temp.get("压缩机2容量"));
            temp2.put("风机1转速",temp.get("风机1转速"));
            temp2.put("风机2转速",temp.get("风机2转速"));
            temp2.put("冷凝风机1转速",temp.get("冷凝风机1转速"));
            temp2.put("冷凝风机2转速",temp.get("冷凝风机2转速"));
            temp2.put("回风温度设定",temp.get("回风温度设定"));
            temp2.put("回风温度1",temp.get("回风温度1"));
            temp2.put("回风温度2",temp.get("回风温度2"));
            temp2.put("回风温度3",temp.get("回风温度3"));
            temp2.put("回风温度4",temp.get("回风温度4"));
            temp2.put("送风温度设定",temp.get("送风温度设定"));
            temp2.put("送风温度1",temp.get("送风温度1"));
            temp2.put("送风温度4",temp.get("送风温度4"));
            kt_all.put(i,temp2);
        }


        Map<String,Object> kt_name = new HashMap<>();
        kt_name.put("机房空调",kt_all);   // 存入键值对 ,样式为（机房空调，{[1，空调1参数键值对],[2,空调2参数键值对]...})）
        list_all.add(kt_name);
        return list_all;
    }




    @CrossOrigin
    @RequestMapping("/getData/202/realdata/servernew2")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
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


    @CrossOrigin
    @RequestMapping("/getData/202/realdata/servernew")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_servernew2(){

        List <Map<String,Object>> list_data= new ArrayList<>();  //储存返回的json
        Map<String, Object> data = new HashMap<String, Object>();

        List<String> server = Arrays.asList("A","B","C","D","E","F","G","H","J","K","L","M","N","P");
        String sql="select Value0 from realdata_once where Location='JF202' and Equipment='服务器A' and SiteName='A1-上' limit 0,1";

        String sql1="select * from realdata_once where Location='JF202' and Equipment='服务器'";

        Map<String, Object> servers_cold= new TreeMap<>();  //所有列列服务器冷通道
        Map<String, Object> servers_hot= new TreeMap<>();  //某列服务器冷通道
        Map<String, Object> servers_power= new TreeMap<>();  //某列服务器冷通道
        Integer siteNum=23;



        Map<String, Object> server_temp_power = new TreeMap<>();  //某列服务器功率 放在服务器外面，包裹所有服务器
        for (String c:server){  //遍历服务器 c为（"A","B","C","D" ...）

//            List <Map<String,Object>> list1= new jdbc.queryForList(sql1);
            Map<String, Object> server_temp_cold= new TreeMap<>();  //某列服务器冷通道
            Map<String, Object> server_temp_hot = new TreeMap<>();  //某列服务器热通道

            Integer cold_up_cnt_null=0;
            Integer cold_down_cnt_null=0;
            Integer hot_cnt_null=0;

            double cold_up_sum=0.0;
            double cold_down_sum=0.0;
            double hot_sum=0.0;//某列服务器热通道测点温度求和

            String sql_temp1=sql1.replace("'服务器'","'服务器"+c+"'"); //某服务器所有测点
            List <Map<String,Object>> list1 =jdbc.queryForList(sql_temp1);
            List <Double>server_site_cold_up =new ArrayList<>();  //某列服务器冷通道上测点
            List <Double>server_site_cold_down =new ArrayList<>();  //某列服务器冷通道下测点
            List <Double>server_site_hot_all =new ArrayList<>();  //某列服务器热通道测点
            Integer cnt=0;
            for(Map<String,Object>l:list1){
                double value0=(double) l.get("Value0");
                if(cnt<siteNum*2){
                    if(cnt%2!=0){//奇数下测点
                        server_site_cold_down.add(value0);//冷上 0 2 4 6 8
                        if(value0<=0.0){
                            cold_up_cnt_null++;
                        }else{
                            cold_up_sum+=value0;
                        }
                    }else{
                        server_site_cold_up.add(value0);//冷下 1 3 5 7
                        if(value0<=0.0){
                            cold_down_cnt_null++;
                        }else{
                            cold_down_sum+=value0;
                        }
                    }
                }else if(cnt<69){
                    server_site_hot_all.add(value0);//热 46 47 48 49
                    if(value0<=0.0){
                        hot_cnt_null++;
                    }else{
                        hot_sum+=value0;
                    }
                }else{ //69
                    server_temp_power.put(c,value0);
                }
                cnt++;

            }

            Map<String, Object> server_site =new HashMap<String, Object>();  //服务器测点

            cold_up_sum=cold_up_sum/(siteNum-cold_up_cnt_null);//某列服务冷通道上测点的总和除非0测点的个数，为某列服务器冷通道上测点平均
            cold_down_sum=cold_down_sum/(siteNum-cold_down_cnt_null);//某列服务冷通道下测点的总和除非0测点的个数，为某列服务器冷通道下测点平均
            hot_sum=hot_sum/(siteNum-hot_cnt_null);//某列服务热通道测点的总和除非0测点的个数，为某列服务器热通道测点平均

            String t1 = String.format("%.2f", cold_up_sum);
            String t2 = String.format("%.2f", cold_down_sum);
            String t3 = String.format("%.2f", hot_sum); // 保留两位小数


            Map<String, Object> site_avg_cold = new TreeMap<>();//某服务器冷通道平均
            Map<String, Object> site_avg_hot = new TreeMap<>();//热通道平均
            Map<String, Object> site_cold = new TreeMap<>(); //冷通道
            Map<String, Object> site_hot = new TreeMap<>();//热通道
            Map<String, Object> site_power = new TreeMap<>();//功率
//            TreeMap<String, Object> server_site22_avg = new TreeMap<>(server_site2_avg);  //测点排序

            site_avg_cold.put("upall",t1);//冷通道上，（upall，某列冷上平均）
            site_avg_cold.put("downall",t2);//冷通道下 （downall，某列冷下平均）
            site_avg_hot.put("all",t3); ////热通道  （all，某列热平均）

            site_cold.put("up",server_site_cold_up); //某列服务器所有上测点  （up，{服务器所有测点（1，22）（2，22）..}）
            site_cold.put("down",server_site_cold_down);//某列服务器所有下测点  （down，{服务器所有测点（1，22）（2，22）..}）
            //返回索引最高和次高温度
            TreeSet<Double> cold_up_temp=new TreeSet<Double>(server_site_cold_up);
            TreeSet<Double> cold_down_temp=new TreeSet<Double>(server_site_cold_down);
            TreeSet<Double> hot_temp=new TreeSet<Double>(server_site_hot_all);
//
//            Collections.sort(cold_up_temp);
//            Collections.sort(cold_down_temp);
//            Collections.sort(hot_temp);

            double cold_max_up1=0.0;
            double cold_max_up2=0.0;
            double cold_max_down1=0.0;
            double cold_max_down2=0.0;

            int cnt_num=1;
            for( Double i:cold_up_temp){
                if(cnt_num==cold_up_temp.size()){
                    cold_max_up1=i;
                }else if(cnt_num==cold_up_temp.size()-1){
                    cold_max_up2=i;
                }
                cnt_num++;
            }

            cnt_num=1;
            for( Double i:cold_down_temp){
                if(cnt_num==cold_down_temp.size()){
                    cold_max_down1=i;//最大
                }else if(cnt_num==cold_down_temp.size()-1){
                    cold_max_down2=i;//次大
                }
                cnt_num++;
            }//找出最大值和次大值

            List <Integer>up_index=new ArrayList<>();
            List <Integer>down_index=new ArrayList<>();
            List <Integer>up2_index=new ArrayList<>();
            List <Integer>down2_index=new ArrayList<>();
            int index_cnt=0;
            for(double i :server_site_cold_up){
                if(i==cold_max_up1){
                    up_index.add(index_cnt);
                }else if(i ==cold_max_up2){
                    up2_index.add(index_cnt);
                }
                index_cnt++;
            }

            index_cnt=0;
            for(double i :server_site_cold_down){
                if(i==cold_max_down1){
                    down_index.add(index_cnt);
                }else if(i ==cold_max_down2){
                    down2_index.add(index_cnt);
                }
                index_cnt++;
            }


//            double hot_max1= (double)cold_up_temp.get(cold_up_temp.size()-1);
//            double hot_max2= (double)cold_up_temp.get(cold_up_temp.size()-2);
            site_cold.put("cold_up_max_index",up_index);  //在数组查找索引
            site_cold.put("cold_up_submax_index",up2_index);
            site_cold.put("cold_down_max_index",down_index);
            site_cold.put("cold_down_submax_index",down2_index);

            server_temp_cold.put("avg",site_avg_cold);  // (avg, {（upall，某列冷上平均）,（downall，某列冷下平均）})
            server_temp_cold.put("sitedetail",site_cold);// .put("sitedetail",serv);//(sitedetail,{（up，{服务器所有测点（1，22）（2，22）..}）,（down，{服务器所有测点（1，22）（2，22）..}）})

            //热通道
            server_temp_hot.put("sitedetail",server_site_hot_all); //(sitedetail,{服务器所有测点（1，22）（2，22）..})
            server_temp_hot.put("avg",site_avg_hot); //(avg,（all，某列热平均）)


            servers_cold.put(c,server_temp_cold); //冷通道（A，{(avg,xx),(sitedetail,xx)}）
            servers_hot.put(c,server_temp_hot); //热通道（A，{}）
//            servers_power.put(c,server_temp_power); //热通道（A，{}）
//            break;
//            server_num+=1;
//            data.put(sql_temp1,c);
        }

//        server_temp2=temp_p;
        data.put("servercold",servers_cold);
        data.put("serverhot",servers_hot);
        data.put("serverpower",server_temp_power);

        list_data.add(data);
        return list_data;
    }



    @CrossOrigin
    @RequestMapping("/getData/202/realdata/server_display")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_servernew3(){

        List <Map<String,Object>> list_data= new ArrayList<>();  //储存返回的json
        Map<String, Object> data = new HashMap<String, Object>();

        List<String> server = Arrays.asList("A","B","C","D","E","F","G","H","J","K","L","M","N","P");
        String sql="select Value0 from realdata_once where Location='JF202' and Equipment='服务器A' and SiteName='A1-上' limit 0,1";

        String sql1="select * from realdata_once where Location='JF202' and Equipment='服务器'";

        Map<String, Object> servers_cold= new TreeMap<>();  //所有列列服务器冷通道
        Map<String, Object> servers_hot= new TreeMap<>();  //某列服务器冷通道
        Map<String, Object> servers_power= new TreeMap<>();  //某列服务器冷通道
        Integer siteNum=23;



        Map<String, Object> server_temp_power = new TreeMap<>();  //某列服务器功率 放在服务器外面，包裹所有服务器
        for (String c:server){  //遍历服务器 c为（"A","B","C","D" ...）

//            List <Map<String,Object>> list1= new jdbc.queryForList(sql1);
            Map<String, Object> server_temp_cold= new TreeMap<>();  //某列服务器冷通道
            Map<String, Object> server_temp_hot = new TreeMap<>();  //某列服务器热通道

            Integer cold_up_cnt_null=0;
            Integer cold_down_cnt_null=0;
            Integer hot_cnt_null=0;

            double cold_up_sum=0.0;
            double cold_down_sum=0.0;
            double hot_sum=0.0;//某列服务器热通道测点温度求和

            String sql_temp1=sql1.replace("'服务器'","'服务器"+c+"'"); //某服务器所有测点
            List <Map<String,Object>> list1 =jdbc.queryForList(sql_temp1);
            List<Double> server_site_cold_up =new ArrayList<>(); //某列服务器冷通道上测点
            List<Double> server_site_cold_down =new ArrayList<>();  //某列服务器冷通道下测点
            List<Double> server_site_hot_all =new ArrayList<>();  //某列服务器热通道测点
            Integer cnt=0;
            for(Map<String,Object>l:list1){
                Double value0=(double) l.get("Value0");
                if(cnt<siteNum*2){
                    if(cnt%2!=0){//奇数下测点
                        if( value0>=26.8){
                            server_site_cold_down.add(1.0);//冷上 0 2 4 6 8  过热1
                        }else if( value0<=0.0){
                            server_site_cold_down.add(-1.0);//+1      异常-1
                        }else{
                            server_site_cold_down.add(0.0);         //正常0
                        }
                    }else{

                        if(value0>=26.8){
                            server_site_cold_up.add(1.0);//冷下 1 3 5 7
                        }else if( value0<=0.0){
                            server_site_cold_up.add(-1.0);
                        }else{
                            server_site_cold_up.add(0.0);
                        }
                    }
                }else if(cnt<69){
                    if((value0<=28.0 & value0>0.0)| value0>=38.0){
                        server_site_hot_all.add(1.0);//热 46 47 48 49
                    }else if( value0<=0.0){
                        server_site_hot_all.add(-1.0);//热 46 47 48 49
                    }else{
                        server_site_hot_all.add(0.0);
                    }
                }
                cnt++;

            }

            Map<String, Object> server_site =new HashMap<String, Object>();  //服务器测点

            cold_up_sum=cold_up_sum/(siteNum-cold_up_cnt_null);//某列服务冷通道上测点的总和除非0测点的个数，为某列服务器冷通道上测点平均
            cold_down_sum=cold_down_sum/(siteNum-cold_down_cnt_null);//某列服务冷通道下测点的总和除非0测点的个数，为某列服务器冷通道下测点平均
            hot_sum=hot_sum/(siteNum-hot_cnt_null);//某列服务热通道测点的总和除非0测点的个数，为某列服务器热通道测点平均

            String t1 = String.format("%.2f", cold_up_sum);
            String t2 = String.format("%.2f", cold_down_sum);
            String t3 = String.format("%.2f", hot_sum); // 保留两位小数


            Map<String, Object> site_avg_cold = new TreeMap<>();//某服务器冷通道平均
            Map<String, Object> site_avg_hot = new TreeMap<>();//热通道平均
            Map<String, Object> site_cold = new TreeMap<>(); //冷通道
            Map<String, Object> site_hot = new TreeMap<>();//热通道
            Map<String, Object> site_power = new TreeMap<>();//功率
//            TreeMap<String, Object> server_site22_avg = new TreeMap<>(server_site2_avg);  //测点排序

            site_avg_cold.put("upall",t1);//冷通道上，（upall，某列冷上平均）
            site_avg_cold.put("downall",t2);//冷通道下 （downall，某列冷下平均）
            site_avg_hot.put("all",t3); ////热通道  （all，某列热平均）

            site_cold.put("up",server_site_cold_up); //某列服务器所有上测点  （up，{服务器所有测点（1，22）（2，22）..}）
            site_cold.put("down",server_site_cold_down);//某列服务器所有下测点  （down，{服务器所有测点（1，22）（2，22）..}）


            server_temp_cold.put("avg",site_avg_cold);  // (avg, {（upall，某列冷上平均）,（downall，某列冷下平均）})
            server_temp_cold.put("sitedetail",site_cold);// .put("sitedetail",serv);//(sitedetail,{（up，{服务器所有测点（1，22）（2，22）..}）,（down，{服务器所有测点（1，22）（2，22）..}）})

            //热通道
            server_temp_hot.put("sitedetail",server_site_hot_all); //(sitedetail,{服务器所有测点（1，22）（2，22）..})
            server_temp_hot.put("avg",site_avg_hot); //(avg,（all，某列热平均）)


            servers_cold.put(c,server_temp_cold); //冷通道（A，{(avg,xx),(sitedetail,xx)}）
            servers_hot.put(c,server_temp_hot); //热通道（A，{}）
//            servers_power.put(c,server_temp_power); //热通道（A，{}）
//            break;
//            server_num+=1;
//            data.put(sql_temp1,c);
        }

//        server_temp2=temp_p;
        data.put("servercold",servers_cold);
        data.put("serverhot",servers_hot);
//        data.put("serverpower",server_temp_power);

        list_data.add(data);
        return list_data;
    }

    ////新代码
    @CrossOrigin
    @RequestMapping("/getData/202/realdata/kt_diagnosis")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
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
                    if((value0>0&value0<28) | value0>38){
                        list_temp.add(site);
                    }
                }
            }
        }

        return list_temp;
    }


}

