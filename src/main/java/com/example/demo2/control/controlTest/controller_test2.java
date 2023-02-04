//package com.example.demo2.control;
//
//
//import com.example.demo2.entity.USER;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.util.JSONPObject;
//import org.apache.catalina.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class controller_test2 {
//
//    @ResponseBody
//    @RequestMapping(value="/try")
//    public String test01() throws JsonProcessingException {
//        USER user= new USER();
//        user.setName("zhangsan");
//        user.setAge(18);
//        user.setMoney(55.5);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String str = objectMapper.writeValueAsString(user);
//        return str;
//    }
//}
