//package com.example.demo2.util;
//
//
//import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.util.List;
//
//@Service
//public class jsondeal {
//    public static String readJson(String filepath){
//        String jsonStr = "";
//        try{
//            File jsonFile = new File(filepath);
//            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
//            int ch=0;
//            StringBuffer sb= new StringBuffer();
//            while((ch= reader.read())!=-1){
//                sb.append((char) ch);
//            }
//            reader.close();
//            jsonStr = sb.toString();
//            return jsonStr;
//        }catch(Exception ex){
//            ex.printStackTrace();
//            return null;
//        }
//    }
//}
