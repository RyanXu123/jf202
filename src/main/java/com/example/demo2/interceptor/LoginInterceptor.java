package com.example.demo2.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo2.entity.ResultMassage;
import com.example.demo2.util.HttpContextUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token= request.getHeader("token");
        if(StringUtils.isBlank(token)){
            setReturn(response,401,"用户未登录，请先登录");
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler, Exception ex){

    }

    private static void setReturn(HttpServletResponse response,Integer code,String msg) throws IOException{
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        ResultMassage result = new ResultMassage(code,msg,"");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        httpResponse.getWriter().print(json);
    }


}
