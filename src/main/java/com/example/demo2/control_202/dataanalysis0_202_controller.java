
package com.example.demo2.control_202;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo2.dto.KtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;


import com.example.demo2.entity.dataanalysis;
import com.example.demo2.service.dataanalysisService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

    @Controller
    public class dataanalysis0_202_controller {
        @Autowired
        dataanalysisService dataService;

        @CrossOrigin
        //    @CrossOrigin
        @RequestMapping("/getData/202/dataanalysisnew")
        @ResponseBody
//        @Scheduled(fixedRate = 30000)
        public List<Map<String,Object>> showAnalysis(){
            ArrayList<KtDto> res = new ArrayList<>();
            List <Map<String,Object>> final0= new ArrayList<>();
            Map<String,Object> kt_all=new LinkedHashMap<>();

            LinkedList<String> Time_line= new LinkedList<>();
            for (int i = 1; i < 21; i++) {
                LambdaQueryWrapper<dataanalysis> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(dataanalysis::getEquipment , "KT"+i);
                List <dataanalysis> list =dataService.list(queryWrapper);
                KtDto dto = new KtDto();
                List<Double> SFD = new LinkedList<>();
                List<Double> SF1 = new LinkedList<>();
                List<Double> SF4 = new LinkedList<>();
                List<Double> HFD = new LinkedList<>();
                List<Double> HF1 = new LinkedList<>();
                List<Double> HF2 = new LinkedList<>();
                List<Double> HF3 = new LinkedList<>();
                List<Double> HF4 = new LinkedList<>();
                List<Double> FJ1 = new LinkedList<>();
                List<Double> FJ2 = new LinkedList<>();
                List<Double> YSJ1 = new LinkedList<>();
                List<Double> YSJ2 = new LinkedList<>();
                List<Double> power = new LinkedList<>();
                dto.setSFD(SFD);
                dto.setFJ1(FJ1);
                dto.setSF1(SF1);
                dto.setSF4(SF4);
                dto.setHFD(HFD);
                dto.setHF1(HF1);
                dto.setHF2(HF2);
                dto.setHF3(HF3);
                dto.setHF4(HF4);
                dto.setFJ2(FJ2);
                dto.setYSJ1(YSJ1);
                dto.setYSJ2(YSJ2);
                dto.setPower(power);

                for(dataanalysis kt : list){
                    SFD.add(kt.getSFD());
                    SF1.add(kt.getSF1());
                    SF4.add(kt.getSF4());
                    HFD.add(kt.getHFD());
                    HF1.add(kt.getHF1());
                    HF2.add(kt.getHF2());
                    HF3.add(kt.getHF3());
                    HF4.add(kt.getHF4());
                    FJ1.add(kt.getFJ1());
                    FJ2.add(kt.getFJ1());
                    YSJ1.add(kt.getYSJ1());
                    YSJ2.add(kt.getYSJ2());
                    power.add(kt.getPower());
                    if (i==1){
                        //第一台空调的时候取时间
                        Time_line.add(kt.getSampleTime());
                    }
                }
                res.add(dto);
            }
            kt_all.put("KT",res);
            kt_all.put("TimeLine",Time_line);
            final0.add(kt_all);
            return final0;
        }
    }


