package com.example.demo2.control_202;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Controller.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class aiparams_202_controller {

    String hot_max="26.8°C";
    String ai_range="1°C";
    String ai_time="30min";




    @CrossOrigin
    @RequestMapping("/getData/202/aiparams")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_aiparams2(){

        List <Map<String,Object>> list2= new ArrayList<>();
        Map<String,Object> temp1= new LinkedHashMap<>();
        Map<String,Object> temp2= new LinkedHashMap<>();
        Map<String,Object> temp3= new LinkedHashMap<>();
        temp1.put("id",1);
        temp1.put("Params","热点检查阈值");
        temp1.put("Value0",hot_max);

        temp2.put("id",2);
        temp2.put("Params","AI群控控制范围");
        temp2.put("Value0",ai_range);

        temp3.put("id",3);
        temp3.put("Params","AI群控控制周期");
        temp3.put("Value0",ai_time);

        list2.add(temp1);
        list2.add(temp2);
        list2.add(temp3);

        return list2;
    }
    @CrossOrigin
    @PostMapping("/getData/202/aiparams")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_aiparams1(@RequestBody List<Map<String,Object>>data){
        hot_max=data.get(0).get("Value0").toString();
        ai_range=data.get(1).get("Value0").toString();
        ai_time=data.get(2).get("Value0").toString();

        return data;
    }

    String Safe="0";
    String Enengy="0";
    @CrossOrigin
    @RequestMapping("/getData/202/aicontrol")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<String> aicontrol1(){
        List ret= new ArrayList<>();

        ret.add(Enengy);
        ret.add(Safe);
        return  ret;
    }

    @CrossOrigin
    @PostMapping("/getData/202/aicontrol")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Integer> aicontrol2(@RequestBody List<Integer> data){
        Safe=data.get(1).toString();  //保存更改的ai控制状态数据
        Enengy=data.get(0).toString(); //保存更改的ai控制状态数据
        return  data;
    }


    @CrossOrigin
    @RequestMapping("/202/AI_data")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public Map<String,Object> params(){
        Map<String,Object> m=new LinkedHashMap<>();
        m.put("热点检查阈值",hot_max);
//        m.put("AI预控控制范围","2℃");
        m.put("AI群控控制范围",ai_range);
        m.put("AI群控控制周期",ai_time);
//        if(Safe=="0" & Enengy== "0"){
//            m.put("AI控制状态","无");
//        }else if(Safe=="1" & Enengy== "0"){
//            m.put("AI控制状态","安全");
//        }else if(Safe=="1" & Enengy== "1"){
//            m.put("AI控制状态","节能且安全");
//        }
        m.put("安全",Safe);
        m.put("节能",Enengy);

        return m;
    }
}
