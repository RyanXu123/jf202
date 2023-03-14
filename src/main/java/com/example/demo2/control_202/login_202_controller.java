//package com.example.demo2.control_202;
//
//import com.example.demo2.dto.LoginDto;
//import com.example.demo2.entity.ResultMassage;
//import com.example.demo2.service.LoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class login_202_controller {
//    @Autowired
//    LoginService loginService;
//
//    @PostMapping("/login")
//    @CrossOrigin
//    @ResponseBody
//    public ResultMassage login(@RequestBody LoginDto loginDto){
//        return loginService.login(loginDto);
//    }
//
//}
