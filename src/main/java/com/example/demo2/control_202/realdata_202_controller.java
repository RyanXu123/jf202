package com.example.demo2.control_202;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo2.entity.alert;
import com.example.demo2.entity.dataanalysis_kt;
import com.example.demo2.entity.sitecold;
import com.example.demo2.mapper.alertMapper;
import com.example.demo2.mapper.sitecoldMapper;
import com.example.demo2.service.alertService;
import com.example.demo2.service.sitecoldService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//import com.example.demo2.control_202.coldsite_updata.*;

import java.sql.Time;
import java.util.*;

@Controller
public class realdata_202_controller {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    alertService alertservice;

    @Autowired(required=false)
    sitecoldService scService;

//    coldsite_updata_controller cuc = new coldsite_updata_controller();



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
    public List<Map<String,Object>> ktnew(){

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
            temp2.put("空调功率",temp.get("空调功率"));
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
                }else if(cnt<siteNum*3){
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


            if(!cold_up_temp.isEmpty()){
                cold_max_up1=cold_up_temp.last();
                cold_up_temp.remove(cold_up_temp.last());
                cold_max_up2=cold_up_temp.last();
            }

//            for( Double i:cold_down_temp){
//                if(cnt_num==cold_down_temp.size()){
//                    cold_max_down1=i;//最大
//                }else if(cnt_num==cold_down_temp.size()-1){
//                    cold_max_down2=i;//次大
//                }
//                cnt_num++;
//            }//找出最大值和次大值
            if(!cold_down_temp.isEmpty()){
                cold_max_down1=cold_down_temp.last();
                cold_down_temp.remove(cold_down_temp.last());
                cold_max_down2=cold_down_temp.last();
            }


            List <Integer>up_index=new ArrayList<>();
            List <Integer>down_index=new ArrayList<>();
            List <Integer>up2_index=new ArrayList<>();
            List <Integer>down2_index=new ArrayList<>();
            int index_cnt=0;
            for(double i :server_site_cold_up){//遍历为最大值的index
                if(i==cold_max_up1 & i>0){ //为最大或次大 但不是废弃测点
                    up_index.add(index_cnt);
                }else if(i ==cold_max_up2 & i>0){//遍历为次大值的index
                    up2_index.add(index_cnt);
                }
                index_cnt++;
            }

            index_cnt=0;
            for(double i :server_site_cold_down){
                if(i==cold_max_down1  & i>0){
                    down_index.add(index_cnt);
                }else if(i ==cold_max_down2  & i>0){
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
    public List<Map<String,Object>> server_display(){

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

    List <Double> sf_range =Arrays.asList(22.0,26.0);
    List  <Double>hf_range =Arrays.asList(32.0,38.0);
    List <Double>ysj_range =Arrays.asList(33.0,100.0);
    List <Double> fj_range =Arrays.asList(33.0,100.0);
    List  <Double>lnfj_range =Arrays.asList(33.0,100.0);
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
                    if(value0<sf_range.get(0) | value0>sf_range.get(1)){
                        list_temp.add(c);
                    }
                }else if(params.equals("回风温度4")|params.equals("回风温度3")|params.equals("回风温度2")|params.equals("回风温度1")){
                    if(value0<hf_range.get(0) | value0>hf_range.get(1)){
                        list_temp.add(c);
                    }
                }else if(params.equals("压缩机1容量")|params.equals("压缩机2容量")){
                    if(value0<ysj_range.get(0)| value0>ysj_range.get(1)){
                        list_temp.add(c);
                    }
                }else if(params.equals("风机1转速")|params.equals("风机2转速")){
                    if(value0<fj_range.get(0) | value0>fj_range.get(1)){
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


    Double fixed_time=10.0;
    Double fixed_range=3.0;
//    Map<String,Object> cold_all_show= new HashMap<>();
//    Map<String,Object> cold_all2= new HashMap<>();
//    Long calendar_start = Calendar.getInstance().getTimeInMillis();

    List<List<String>> cold_list= new ArrayList<>();
    Integer start_flag=0;
//    public Map<String,Object> cold_all_show= new HashMap<>();
    @CrossOrigin
    @RequestMapping("/getData/202/realdata/coldsite_change")
    @ResponseBody
    public List<Map<String,Object>> coldsite_change(){


        List <Map<String,Object>> list_data= new ArrayList<>();  //储存返回的json
        List<String> server = Arrays.asList("A","B","C","D","E","F","G","H","J","K","L","M","N","P");
        Collections.reverse(server);//从P开始排序
        String sql1="select * from realdata_once where Location='JF202' and Equipment='服务器'";

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

                if(cnt_change_site==siteNum*2){
                    break;
                }
                String SiteName= l.get("SiteName").toString();
                Double value0 = (double) l.get("Value0");
//                Double value_before= Double.parseDouble(cold_all_show.get(SiteName).toString());
                Double value_before=cold_before_all.next().getGapValue();
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
        ret.add(fixed_range);
        ret.add(fixed_time);
        return ret;
    }

    @CrossOrigin
    @PostMapping("/getData/202/realdata/cold_detect_design")
    @ResponseBody
    public List<Double> cold_detect_design2(@RequestBody List<Double>data ){
//        List<Double> ret=new ArrayList<>();
        fixed_range=data.get(0);
        fixed_time=data.get(1);
        return data;
    }

    Boolean pre_alert=false;
    Boolean real_alert=false;
    Boolean coldsite_alert=false;
    @CrossOrigin
    @PostMapping("/getData/202/realdata/alert_design")
    @ResponseBody
    public String alert_design(@RequestBody List<Boolean>data ){
//        List<Double> ret=new ArrayList<>();
        real_alert=data.get(0);
        pre_alert=data.get(1);
        coldsite_alert=data.get(2);
        return "receive the alert design params";
    }

    @CrossOrigin
    @RequestMapping("/getData/202/realdata/alert_design")
    @ResponseBody
    public List<Boolean> alert_design0(){
//        List<Double> ret=new ArrayList<>();
        return Arrays.asList(real_alert,pre_alert,coldsite_alert);
    }

    Map<Integer,String> alert_content = new HashMap<>();
    @Autowired
    private sitecoldMapper sitecoldmapper;
    Integer cnt=0;
    @CrossOrigin
    @RequestMapping("/getData/202/alert")
    @ResponseBody
    public Map<String,Object> alert2(){

        Map<String,Object> b= new HashMap<>();
        List<List<String>> now= new ArrayList<>();
        List<List<String>> pre= new ArrayList<>();
        List<List<String>> cold_list= new ArrayList<>();
        List <sitecold> list_sitecold= sitecoldmapper.selectList(null);

//        <sitecold> find_list = new LambdaQueryWrapper<>();
//        find_list.allEq(null);

        String sql2="select * from predata where PointName='冷通道最大温度' ORDER BY id DESC limit 0,7"; //预测警告

        List <Map<String,Object>> list2=jdbc.queryForList(sql2);
        for (Map<String,Object> m:list2){
            if(Double.parseDouble(m.get("Value0").toString())>= (double)cold_range.get(1)){
                pre.add(Arrays.asList(m.get("time").toString(),m.get("Equipment").toString().substring(3),m.get("PointName").toString()+"为"+m.get("Value0")+"°C"));
            }
        }

        String sql=" select * from realdata_once where Location='JF202' and PointName='冷通道温度' ";
        List <Map<String,Object>> cold_temp_all=jdbc.queryForList(sql);


        Integer id=1;
        Iterator<sitecold> cold_before_all = list_sitecold.iterator();
        Iterator<Map<String,Object>> cold_all = cold_temp_all.iterator();

        while(cold_all.hasNext() && cold_before_all.hasNext()){
            sitecold cold_before = cold_before_all.next();
            Map<String,Object> cold= cold_all.next();

            Double value0= (double) cold.get("Value0");
            String SiteName= cold.get("SiteName").toString();
//            Double value_before=Double.parseDouble(cuc.cold_all_show.get(SiteName).toString());
            Double value_before=(double) cold_before.getGapValue();
            if(Math.abs(value0-value_before)>fixed_range){
                cold_list.add(Arrays.asList(id.toString(),cold.get("time").toString(),cold.get("Equipment").toString().substring(3),cold.get("SiteName").toString()+"波动"+String.format("%.2f",Math.abs(value0-value_before))+"度"));
                cold_list.add(Arrays.asList(id.toString(),cold.get("time").toString(),cold.get("Equipment").toString().substring(3),cold.get("SiteName").toString()+"波动"+String.format("%.2f",Math.abs(value0-value_before))+"度"));
                alert alert0 = new alert();
                alert0.setContent(cold.get("SiteName").toString()+"波动"+Math.abs(value0-value_before)+"度");
                alert0.setEquipment(cold.get("Equipment").toString().substring(3));
                alert0.setLocation("FT202");
                alert0.setSampleTime(cold.get("time").toString());
                alertservice.save(alert0);
            }
            id++;
        }

        List<List<String>> temp= new ArrayList<>();
        if(real_alert==true){//实时报警
            b.put("real_hot",temp);
        }else{
            b.put("real_hot",temp);
        }
        if (pre_alert==true){//预控报警
            b.put("pre_hot",pre);
        }else{
            b.put("pre_hot",temp);
        }

        if(coldsite_alert==true){//波动报警
            b.put("cold_change",cold_list);
//            cold_list.clear();
        }else{
            b.put("cold_change",temp);

        }

        return b;
    }


    @CrossOrigin
    @RequestMapping("/getData/202/alert_history")
    @ResponseBody
    public List<alert> alert_history(){
        LambdaQueryWrapper<alert> andWrapper = new LambdaQueryWrapper<>();
        andWrapper.last("limit 1000");
        List <alert> list =alertservice.list(andWrapper);
//        return new HashMap<>();
        return list;
    }



    @CrossOrigin
    @RequestMapping("/getData/202/realdata/diagnosis_kt_design")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List <List<Double>> diagnosis_design(){
        List <List<Double>> ret =new ArrayList<>();
        List temp= Arrays.asList(22,26);
        ret.add(sf_range);
        ret.add(hf_range);
        ret.add(ysj_range);
        ret.add(fj_range);
        ret.add(lnfj_range);
        return ret;
    }

    @CrossOrigin
    @PostMapping("/getData/202/realdata/diagnosis_kt_design")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List <List<Double>> diagnosis_design2(@RequestBody List <List<Double>> data){
        sf_range =(List<Double>)data.get(0);
        hf_range =(List<Double>)data.get(1);
        ysj_range =(List<Double>)data.get(2);
        fj_range =(List<Double>)data.get(3);
        lnfj_range =(List<Double>)data.get(4);
        return data;
    }


    List  cold_range =Arrays.asList(20.0,26.8);
    List  hot_range =Arrays.asList(28.0,38.0);
    @CrossOrigin
    @RequestMapping("/getData/202/realdata/diagnosis_server_design")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List <List<Double>> diagnosis_server_design(){
        List <List<Double>> ret =new ArrayList<>();
        ret.add(cold_range);
        ret.add(hot_range);
        return ret;
    }

    @CrossOrigin
    @PostMapping("/getData/202/realdata/diagnosis_server_design")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List <List<Double>> diagnosis_server_design2(@RequestBody List <List<Double>> data){
//        List <List<Double>> ret =new ArrayList<>();
        cold_range =(List<Double>) data.get(0);
        hot_range =(List<Double>) data.get(1);
        return data;
    }
}

