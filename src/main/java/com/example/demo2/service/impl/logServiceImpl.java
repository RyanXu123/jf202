package com.example.demo2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo2.entity.log;
import com.example.demo2.mapper.logMapper;
import com.example.demo2.service.logService;
import org.springframework.stereotype.Service;

@Service
public class logServiceImpl extends ServiceImpl<logMapper, log> implements logService {
}
