package com.example.demo2.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class MysqlTestController {
    @Autowired
    private JdbcTemplate jdbc;

    @CrossOrigin
    @RequestMapping("/getData/202/kt1")
    @ResponseBody
    public List<Map<String,Object>> getdata202_1(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 AND 对象='空调1' limit 0,13" ;
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt2")
    @ResponseBody
    public List<Map<String,Object>> getdata202_2(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调2' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt3")
    @ResponseBody
    public List<Map<String,Object>> getdata202_3(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调3' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt4")
    @ResponseBody
    public List<Map<String,Object>> getdata202_4(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调4' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt5")
    @ResponseBody
    public List<Map<String,Object>> getdata202_5(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调5' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt6")
    @ResponseBody
    public List<Map<String,Object>> getdata202_6(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调6' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt7")
    @ResponseBody
    public List<Map<String,Object>> getdata202_7(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调7' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt8")
    @ResponseBody
    public List<Map<String,Object>> getdata202_8(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调8' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt9")
    @ResponseBody
    public List<Map<String,Object>> getdata202_9(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调9' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt10")
    @ResponseBody
    public List<Map<String,Object>> getdata202_10(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调10' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt11")
    @ResponseBody
    public List<Map<String,Object>> getdata202_11(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调11' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt12")

    @ResponseBody
    public List<Map<String,Object>> getdata202_12(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调12' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt13")
    @ResponseBody
    public List<Map<String,Object>> getdata202_13(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调13' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt14")
    @ResponseBody
    public List<Map<String,Object>> getdata202_14(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调14' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt15")
    @ResponseBody
    public List<Map<String,Object>> getdata202_15(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调15' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt16")
    @ResponseBody
    public List<Map<String,Object>> getdata202_16(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调16' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt17")
    @ResponseBody
    public List<Map<String,Object>> getdata202_17(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调17' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt18")
    @ResponseBody
    public List<Map<String,Object>> getdata202_18(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调18' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt19")
    @ResponseBody
    public List<Map<String,Object>> getdata202_19(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调19' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/kt20")
    @ResponseBody
    public List<Map<String,Object>> getdata202_20(){
        String sql="select 参数,对象,数值 from realtime4 where 机房=202 and 对象='空调20' limit 0,13";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqa")
    @ResponseBody
    public List<Map<String,Object>> getdata202_a(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器A'limit 0,176";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqb")
    @ResponseBody
    public List<Map<String,Object>> getdata202_b(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器B' limit 0,176";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqc")
    @ResponseBody
    public List<Map<String,Object>> getdata202_c(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器C' limit 0,146";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqd")
    @ResponseBody
    public List<Map<String,Object>> getdata202_d(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器D' limit 0,146";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqe")
    @ResponseBody
    public List<Map<String,Object>> getdata202_e(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器E' limit 0,97";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqf")
    @ResponseBody
    public List<Map<String,Object>> getdata202_f(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器F' limit 0,97";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqg")
    @ResponseBody
    public List<Map<String,Object>> getdata202_g(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器G' limit 0,83";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqh")
    @ResponseBody
    public List<Map<String,Object>> getdata202_h(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器H' limit 0,83";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqj")
    @ResponseBody
    public List<Map<String,Object>> getdata202_j(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器J' limit 0,115";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqk")
    @ResponseBody
    public List<Map<String,Object>> getdata202_k(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器K' limit 0,115";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwql")
    @ResponseBody
    public List<Map<String,Object>> getdata202_l(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器L' limit 0,88";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqm")
    @ResponseBody
    public List<Map<String,Object>> getdata202_m(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器M' limit 0,88";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqn")
    @ResponseBody
    public List<Map<String,Object>> getdata202_n(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器N' limit 0,70";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }
    @CrossOrigin
    @RequestMapping("/getData/202/fwqp")
    @ResponseBody
    public List<Map<String,Object>> getdata202_p(){
        String sql="select 参数,对象,端口,数值 from realtime4 where 机房=202 and 对象='服务器P' limit 0,70";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }

    @CrossOrigin
    @RequestMapping("/getData/203")
    @ResponseBody
    public List<Map<String,Object>> getdata203(){
        String sql="select * from realtime4 where 机房=203 limit 0,1874";
        List <Map<String,Object>> list=jdbc.queryForList(sql);
        return list;
    }

    public class AllowOriginIntercepter implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            return true;

        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        }
        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        }

    }

}

