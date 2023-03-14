package com.example.demo2.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId
    private Integer id;
    private String LoginName;
    private String UserName;
    private String PassWord;
}
