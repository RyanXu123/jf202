package com.example.demo2.selfoperate;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo2.entity.sitecold;
import com.example.demo2.mapper.sitecoldMapper;
import com.example.demo2.service.sitecoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class sitecold_insert implements ApplicationRunner {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private sitecoldService sitecoldservice;

    @Autowired
    sitecoldMapper sitecoldmapper;

    @Override
    public  void run(ApplicationArguments args) throws Exception{
        Integer cnt=0;
        while(true){
            System.out.println("test");
            List<sitecold> list_site_cold= new ArrayList<>();
//            sitecold sc= new sitecold();

            String sql0=" truncate sitecold";
            String sql=" select Value0,SiteName from realdata_once where Location='JF202' and PointName='冷通道温度' ";
//            jdbc.execute(sql0); //清空一下sitecold表格
            List<Map<String,Object>> cold_temp_all=jdbc.queryForList(sql);

            if(cnt==0){
                for(Map<String,Object> c :cold_temp_all ){//更新上一时刻测点温度
                    sitecold sc= new sitecold();
                    sc.setLocation("JF202");
                    sc.setPointName(c.get("SiteName").toString());
                    sc.setGapValue(Double.parseDouble(c.get("Value0").toString()));
//                sitecoldservice.updateBatchById(sc);
                }
                cnt+=1;
            }else{

                for(Map<String,Object> c :cold_temp_all ){//更新上一时刻测点温度
                    UpdateWrapper<sitecold> updatawrapper = new UpdateWrapper<>();
                    updatawrapper.eq("PointName",c.get("SiteName").toString()).set("GapValue",Double.parseDouble(c.get("Value0").toString()));//更新,就不会出现空的情况
                    sitecoldmapper.update(null,updatawrapper);
                }
            }
            Thread.sleep(10000);
        }
    }

}
