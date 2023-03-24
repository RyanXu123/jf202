package com.example.demo2.control_202;

import com.example.demo2.entity.sitecold;
import com.example.demo2.service.sitecoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class sitecold_updata_controller {
//
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired(required=false)
    sitecoldService scService;
    public Map<String,Object> cold_all_show ;
    public void run() throws InterruptedException{
        while(true){
            List<sitecold> list_site_cold= new ArrayList<>();
//            sitecold sc= new sitecold();

            String sql=" select Value0,SiteName from realdata_once where Location='JF202' and PointName='冷通道温度' ";
            List<Map<String,Object>> cold_temp_all=jdbc.queryForList(sql);

            for(Map<String,Object> c :cold_temp_all ){//更新上一时刻测点温度
                sitecold sc= new sitecold();
                sc.setLocation("JF202");
                sc.setPointName(c.get("SiteName").toString());
                sc.setGapValue(Double.parseDouble(c.get("Value0").toString()));
                scService.save(sc);
            }
            Thread.sleep(300000);
        }

    }


}
