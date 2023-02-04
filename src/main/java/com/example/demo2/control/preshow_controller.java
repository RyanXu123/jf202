package com.example.demo2.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class preshow_controller {


    @Autowired
    private JdbcTemplate jdbc;
    @CrossOrigin
    @RequestMapping("/getData/202/preshow")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_pre1() {
        String sql = "select * from predata where Location='JF202' limit 0,1360";
        String sql1 = "select * from preshow where Location='JF202' limit 0,1360";
        List<Map<String, Object>> list = jdbc.queryForList(sql);
        List<Map<String, Object>> list2 = jdbc.queryForList(sql1);
        list.addAll((list2));
        return list;
    }
    @RequestMapping("/getData/202/preshow/realtime")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_real() {
        String sql ="select * from predata where Location='JF202' limit 0,1360";
        List<Map<String, Object>> list = jdbc.queryForList(sql);
        return list;
    }
    @RequestMapping("/getData/202/preshow/pretime")
    @ResponseBody
    @Scheduled(fixedRate = 30000)
    public List<Map<String,Object>> getdata202_pre() {
        String sql = "select * from preshow where Location='JF202' limit 0,1360";
        List<Map<String, Object>> list = jdbc.queryForList(sql);
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
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/servercold/AB")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow1(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器AB'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/servercold/AB")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow2(){
//        String sql="select * from predata where Location='JF202' and Type='预测'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器AB'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/serverpower/AB")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow2101(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and  PointName='服务器功率'  and Equipment='服务器AB'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/serverpower/AB")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow211(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and  PointName='服务器功率' and Equipment='服务器AB'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/servercold/CD")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow3(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器CD'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/servercold/CD")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow4(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器CD'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/servercold/EF")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow5(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器EF'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/servercold/EF")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow6(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器EF'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/servercold/GH")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow7(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器GH'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/servercold/GH")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow8(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器GH'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/servercold/JK")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow9(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器JK'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/servercold/JK")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow10(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器JK'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/servercold/LM")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow11(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器LM'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/servercold/LM")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow12(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器LM'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/servercold/NP")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow13(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器NP'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/servercold/NP")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow14(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='冷通道平均温度' or PointName='冷通道最大温度') and  Equipment='服务器NP'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/serverhot/AB")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow15(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='热通道平均温度') and  Equipment='服务器AB'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/serverhot/AB")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow21(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='热通道平均温度') and  Equipment='服务器AB'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/serverhot/CD")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow31(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='热通道平均温度') and  Equipment='服务器CD'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/serverhot/CD")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow41(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='热通道平均温度') and  Equipment='服务器CD'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/serverhot/EF")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow51(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='热通道平均温度') and  Equipment='服务器EF'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/serverhot/EF")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow61(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='热通道平均温度' ) and  Equipment='服务器EF'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/serverhot/GH")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow71(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='热通道平均温度' ) and  Equipment='服务器GH'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/serverhot/GH")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow81(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='热通道平均温度' ) and  Equipment='服务器GH'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/serverhot/JK")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow91(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='热通道平均温度' ) and  Equipment='服务器JK'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/serverhot/JK")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow101(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='热通道平均温度') and  Equipment='服务器JK'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/serverhot/LM")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow111(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='热通道平均温度' ) and  Equipment='服务器LM'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/serverhot/LM")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow121(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='热通道平均温度') and  Equipment='服务器LM'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/realtime/serverhot/NP")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow131(){
//        String sql="select * from preshow where Location='JF202' and Type='实时'and (PointName='热通道平均温度') and  Equipment='服务器NP'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }
//
//    @CrossOrigin
//    @RequestMapping("/getData/202/preshow/pretime/serverhot/NP")
//    @ResponseBody
//    public List<Map<String,Object>> getdata202_preshow141(){
//        String sql="select * from preshow where Location='JF202' and Type='预测'and (PointName='热通道平均温度') and  Equipment='服务器NP'";
//        List <Map<String,Object>> list=jdbc.queryForList(sql);
//        return list;
//    }


}
