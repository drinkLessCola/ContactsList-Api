package com.ziran.addresslist.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ziran.addresslist.entity.Users;
import com.ziran.addresslist.service.UsersService;
import common.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private TokenUtil tokenUtil = new TokenUtil();
    @Autowired
    private UsersService usersService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("request:" + request.getRequestURI());
        if ("OPTIONS".equals(request.getMethod())) {
            //true是直接放行
            return true;
        }
        // 查看请求中是否存在token，如果不存在直接跳转到登陆页面
        String token = tokenUtil.getToken(request);

        if (StrUtil.isBlank(token)) {
            System.out.println("没有 token" + token);
            JSONObject res = new JSONObject();
            res.put("code", 401);
            res.put("msg", "未登录，请先登录");
            returnJson(response, JSON.toJSONString(res));
            return false;
        }
        String msg;
        //有token,获取token中的用户id
        Integer userId;
        System.out.println("检查 token!");
        try {
            //根据token中的userId查询数据库
            userId = Integer.valueOf(TokenUtil.getUserId(token));
            Users user = usersService.getUserById(userId);
            if (user == null) {
                JSONObject res = new JSONObject();
                res.put("code", 401);
                res.put("msg", "用户不存在，请重新登录");
                returnJson(response, JSON.toJSONString(res));
                return false;
            }
            //验证token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(token);
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!!!!!!!这里");
            JSONObject res = new JSONObject();
            res.put("code", 401);
            res.put("msg", "token验证失败，请重新登录");
            returnJson(response, JSON.toJSONString(res));
            return false;
        }

        return true;
    }
    private void returnJson(HttpServletResponse response, String result) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}