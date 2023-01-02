package com.ziran.addresslist.interceptor;

import cn.hutool.core.util.StrUtil;
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

public class AuthenticationInterceptor implements HandlerInterceptor {
    private TokenUtil tokenUtil = new TokenUtil();
    @Autowired
    private UsersService usersService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("request:" + request.getRequestURI());
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 查看请求中是否存在token，如果不存在直接跳转到登陆页面
        String token = tokenUtil.getToken(request);
        if (StrUtil.isBlank(token)) {
//            response.sendRedirect("/login");
            return false;
        }
        //有token,获取token中的用户id
        Integer userId;
        try {
            userId = Integer.valueOf(TokenUtil.getUserId(token));
        } catch (JWTDecodeException j) {
            throw new RuntimeException("token验证失败，请重新登录");
        }
        //根据token中的userId查询数据库
        Users user = usersService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在，请重新登录");
        }
        //验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("token验证失败，请重新登录");
        }
        return true;
    }
}