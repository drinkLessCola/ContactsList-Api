package com.ziran.addresslist.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ziran.addresslist.entity.Users;
import com.ziran.addresslist.model.LoginData;
import com.ziran.addresslist.service.LoginService;
import common.Constant;
import common.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    /***
     * 注册
     * @param userData 用户注册信息
     * @return
     */
    @PostMapping("/register")
    public JSONObject register(@RequestBody LoginData userData) {
        JSONObject result = new JSONObject();
        // 生成令牌
        int res = loginService.register(userData);
        int code = 200;
        String msg = "注册成功";
        switch (res) {
            case 0: code = 400; msg = "注册失败，请重试"; break;
            case 1: code = 400; msg = "用户名已存在"; break;
        }

        result.put("code", code);
        result.put("msg", msg);
        return result;
    }
    /**
     * 登录
     *
     * @param loginData 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody LoginData loginData, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        // 生成令牌, 并设置 cookie
        Users user = loginService.login(loginData);

        if(user == null) {
            result.put("code", 401);
            result.put("msg", "账号或密码错误");
        } else {
            String token = TokenUtil.generateToken(user);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            JSONObject data = new JSONObject();
            data.put("token", token);
            data.put("userId", user.getUserId());
            data.put("userName", user.getUserName());

            result.put("code", 200);
            result.put("msg", "success");
            result.put("data", data);
        }
        return result;
    }
}