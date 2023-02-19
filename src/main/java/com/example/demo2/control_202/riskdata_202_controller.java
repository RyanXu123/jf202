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
public class riskdata_202_controller {

    @Autowired
    private JdbcTemplate jdbc;
    Map<String,String> risk_list=new HashMap<>();
    Integer cnt=0;//统计数据筛选的次数，满24小时清空一次，即2880

    @CrossOrigin
    @RequestMapping("/getData/202/riskdatanew")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_p(){

        List <Map<String,Object>> list_data= new ArrayList<>();  //储存返回的json
        Map<String, Object> data = new HashMap<String, Object>();

        List<String> server = Arrays.asList("A","B","C","D","E","F","G","H","J","K","L","M","N","P");
        String sql="select Value0 from realdata_once where Location='JF202' and Equipment='服务器A' and SiteName='A1-上' limit 0,1";
        String sql1="select * from realdata_once where Location='JF202' and Equipment='服务器' and SiteName='X1-上' ";
        String sql2="select * from realdata_once where Location='JF202' and Equipment='服务器' and SiteName='X1-下' ";
//        List list_value= new ArrayList<>();
        Integer server_num=1;
//        List list_risk= new ArrayList<>();
//        List list_name= new ArrayList<>();

        List <Map<String,Object>> list1= new ArrayList<>();
        List <Map<String,Object>> list2= new ArrayList<>();
        Map<String, Object> server_temp = new HashMap<String, Object>();  //服务器
        for (String c:server){  //遍历服务器
            String sql_temp1=sql1.replace("'服务器'","'服务器"+c+"'");
            String sql_temp2=sql2.replace("'服务器'","'服务器"+c+"'");

            Map<Integer, Object> server_site1 =new HashMap<Integer, Object>();  //服务器上测点
            Map<Integer, Object> server_site2 =new HashMap<Integer, Object>();  //服务器下测点
            Map<String, Object> server_site =new HashMap<String, Object>();  //服务器测点

            for(Integer i=1;i<=23;i++){ //遍历测点
                String sql_temp11=sql_temp1.replace("'X1","'"+c+i.toString());    //新建变量，否则只能替换第一次，后面X1变成A1
                String sql_temp22=sql_temp2.replace("'X1","'"+c+i.toString());
                list1=jdbc.queryForList(sql_temp11);
                list2=jdbc.queryForList(sql_temp22);

                String riskStr = new String();
                String sitename = new String();
                Integer num0 =0 ;

                double old=0.0;
                for (Map<String,Object> l:list2) {
                    Object temp_name1 = l.get("SiteName");
                    sitename = (temp_name1.toString());
                    int stop_point = sitename.indexOf('-');  //截取数字
                    String num = sitename.substring(1, stop_point);
                    num0 = new Integer(num);

                    Object temp_value = l.get("Value0");
                    Double risk = Double.parseDouble(temp_value.toString());
                    risk=(risk/46);
                    if (risk == 0.0) {
                        risk = -1.0;
                    }
                    riskStr = String.format("%.2f", risk);
                }

                server_site1.put(num0,riskStr); //每一个测点和他的risk

                old=0.0;
                for (Map<String,Object> l:list2) {
                    Object temp_name1 = l.get("SiteName");
                    sitename = (temp_name1.toString());
                    int stop_point = sitename.indexOf('-');  //截取数字
                    String num = sitename.substring(1, stop_point);
                    num0 = new Integer(num);

                    Object temp_value = l.get("Value0");
                    Double risk = Double.parseDouble(temp_value.toString());
                    risk=(risk/46);
                    if (risk == 0.0) {
                        risk = -1.0;
                    }
                    riskStr = String.format("%.2f", risk);
                }





//                  111111111111111111111111
//                    else{
//                        String test=risk_list.get(sitename);
//                        if(test==null){
//                            old=0.0;
//                        }else{
//                            old=Double.parseDouble(risk_list.get(sitename));
//                        }
////                        Double.parseDouble(test);
//                        System.out.print(risk);
//                        old=(old*cnt+risk)/(cnt+1);
//                        risk=old;
//                        risk_list.put(sitename,String.valueOf(risk));
//                    }
//                    riskStr = String.format("%.2f", risk/46); //负数也除了 所以特别小
//                    cnt+=1;
//                }
//                11111111111111111111111111
                server_site2.put(num0,riskStr);
//                list_data.addAll(list1);
//                list_data.addAll(list2);
//                data.put(sql_temp1,Arrays.asList(c,i));  //debugs
                cnt+=1;
                if(cnt==2880){
                    cnt=0;
                }
            }
            TreeMap<Integer, Object> server_site11 = new TreeMap<>(server_site1);
            TreeMap<Integer, Object> server_site22 = new TreeMap<>(server_site2);
            server_site.put(c+"上",server_site11);
            server_site.put(c+"下",server_site22);
            server_temp.put(c,server_site);
        }


        data.put("202",server_temp);
        list_data.add(data);
//        list_data.add(0,(Object)Map<"test",risk_list>);
        return list_data;

    }



    @CrossOrigin
    @RequestMapping("/getData/202/riskdatanew0216")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_p2(){

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
        for (String c:server) {  //遍历服务器 c为（"A","B","C","D" ...）

//            List <Map<String,Object>> list1= new jdbc.queryForList(sql1);
            Map<String, Object> server_temp_cold = new TreeMap<>();  //某列服务器冷通道
            Map<String, Object> server_temp_hot = new TreeMap<>();  //某列服务器热通道


            String sql_temp1 = sql1.replace("'服务器'", "'服务器" + c + "'"); //某服务器所有测点
            List<Map<String, Object>> list1 = jdbc.queryForList(sql_temp1);
            List<Double> server_site_cold_up = new ArrayList<>(); //某列服务器冷通道上测点
            List<Double> server_site_cold_down = new ArrayList<>();  //某列服务器冷通道下测点
            List<Double> server_site_hot_all = new ArrayList<>();  //某列服务器热通道测点
            Integer cnt = 0;
            for (Map<String, Object> l : list1) {
                Double value0 = (double) l.get("Value0");
                if (cnt < siteNum * 2) {
                    if (cnt % 2 != 0) {//奇数下测点

                        String s= String.format("%.2f", value0 / 50.0);
                        double d =Double.parseDouble(s);
                        if (value0==0.0){
                            d=-1.0;
                        }
                        server_site_cold_down.add(d);         //正常0
                    } else {
                        String s= String.format("%.2f", value0 / 50.0);

                        double d =Double.parseDouble(s);
                        if (value0==0.0){
                            d=-1.0;
                        }
                        server_site_cold_up.add(d);//冷下 1 3 5 7
                    }
                cnt++;
                }

                Map<String, Object> server_site = new HashMap<String, Object>();  //服务器测点
                Map<String, Object> site_cold = new TreeMap<>(); //冷通道
//            TreeMap<String, Object> server_site22_avg = new TreeMap<>(server_site2_avg);  //测点排序


                site_cold.put("up", server_site_cold_up); //某列服务器所有上测点  （up，{服务器所有测点（1，22）（2，22）..}）
                site_cold.put("down", server_site_cold_down);//某列服务器所有下测点  （down，{服务器所有测点（1，22）（2，22）..}）

                servers_cold.put(c, site_cold); //冷通道（A，{(avg,xx),(sitedetail,xx)}）
            }

        }
        list_data.add(servers_cold);
        return list_data;
    }

}
