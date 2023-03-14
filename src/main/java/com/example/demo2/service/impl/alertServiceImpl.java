package com.example.demo2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo2.entity.alert;
import com.example.demo2.mapper.alertMapper;
import com.example.demo2.service.alertService;
import org.springframework.stereotype.Service;

@Service
public class alertServiceImpl extends ServiceImpl<alertMapper, alert> implements alertService {
}
