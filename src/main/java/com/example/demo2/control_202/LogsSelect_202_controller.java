package com.example.demo2.control_202;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class LogsSelect_202_controller {
    @Autowired
    private JdbcTemplate jdbc;

    @CrossOrigin
    @RequestMapping("/getData/202/logs")
    @ResponseBody
//    @Scheduled(fixedRate = 30000)
    public List<Map<String, Object>> LogsSelect() {
        String sql = "select * from log";
        List<Map<String, Object>> list = jdbc.queryForList(sql);
        return list;
    }

}
