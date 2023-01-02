package com.ziran.addresslist.controller;

import com.alibaba.fastjson.JSONObject;
import com.ziran.addresslist.entity.Contacts;
import com.ziran.addresslist.model.UserInfoData;
import com.ziran.addresslist.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/userinfo")
public class UserinfoController {
    @Autowired
    private UserinfoService userinfoService;


    @GetMapping("/get/{userId}")
    public JSONObject getUserInfo(@PathVariable("userId") Integer userId) {
        Contacts data = userinfoService.getUserInfo(userId);
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", data);
        return result;
    }

    @PutMapping("/update")
    public JSONObject addUserInfo(@RequestBody Contacts newContact) {
        boolean res = userinfoService.updateUserInfo(newContact);
        JSONObject result = new JSONObject();
        if(res) {
            result.put("code", 200);
            result.put("msg", "设置成功！");
        } else {
            result.put("code", 400);
            result.put("msg", "设置失败");
        }
        return result;
    }

}
