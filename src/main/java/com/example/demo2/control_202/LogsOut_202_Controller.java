package com.example.demo2.control_202;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class LogsOut_202_Controller {
    @Autowired
    private JdbcTemplate jdbc;

    @CrossOrigin
    @RequestMapping("/getData/202/logs/out")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String, Object>> LogsSelect() {
        String sql = "select * from log";
        List<Map<String, Object>> list = jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @PostMapping("/getData/202/logs")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String, Object>> LogsSelect_one(@RequestBody List<String> data) {
        String sql = "select * from log";
        String start_time=data.get(0).toString();
        String end_time=data.get(1).toString();
        sql=sql.concat(" where time >'"+start_time+"' and time <'"+end_time+"';");
        List<Map<String, Object>> list = jdbc.queryForList(sql);
        return list;
    }

}
