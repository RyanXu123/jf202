package com.example.demo2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.example.demo2.entity.dataanalysis;
import com.example.demo2.service.dataanalysisService;
import com.example.demo2.mapper.dataanalysisMapper;

@Service
public class dataanalysisServiceImpl extends ServiceImpl<dataanalysisMapper,dataanalysis> implements dataanalysisService {
}
