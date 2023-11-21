package com.example.demo2.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String role;
    private String loginName;
    private String password;
    //省略getter、setter
}
