//package com.example.demo2.control;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.*;
//
//
//@RestController
////@RequestMapping("/RealTime1")
//public class controller_json_test{
//
//    String PATH = "C:\\Users\\yuduw\\Documents\\WeChat Files\\wxid_eq5qe2802kio22\\FileStorage\\File\\2023-01\\";
//
//    public static void main(String[] args) throws IOException {
//        controller_json_test.readJson("C:\\Users\\yuduw\\Documents\\WeChat Files\\wxid_eq5qe2802kio22\\FileStorage\\File\\2023-01\\202.json");
//    }
//
//    @RequestMapping("/202")
//    @ResponseBody
//    public static String readJson(String str) throws IOException {
//        File file = new File(str);
//        //FileReader fileReader = new FileReader(file);
//        Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
//        int ch = 0;
//        StringBuffer sb = new StringBuffer();
//        while ((ch = reader.read()) != -1) {
//            sb.append((char) ch);
//            System.out.print(ch);
//        }
//        reader.close();
//
//        String jsonStr = sb.toString();
//        System.out.print(jsonStr);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String ret = objectMapper.writeValueAsString(jsonStr);
//        return "nishishabi";
//    }
//
//}
