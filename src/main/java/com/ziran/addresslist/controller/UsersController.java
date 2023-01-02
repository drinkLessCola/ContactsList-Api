package com.ziran.addresslist.controller;

import com.alibaba.fastjson.JSONObject;
import com.ziran.addresslist.entity.Contacts;
import com.ziran.addresslist.entity.Users;
import com.ziran.addresslist.model.KeywordData;
import com.ziran.addresslist.model.UserInfoData;
import com.ziran.addresslist.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    /**
     * 获取所有的用户
     * @return
     */
    @GetMapping("/users/get")
    public JSONObject getAllUsers() {
        System.out.println("get Users");
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data",usersService.getAllUsers());
        return result;
    }

    @PostMapping("/users/get")
    public JSONObject findUser(@RequestBody KeywordData keywordData){
        String keyword = keywordData.getKeyword();
        List<Contacts> users = usersService.findUser(keyword);

        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("total", users.size());
        result.put("data", users);
        return result;
    }
}
