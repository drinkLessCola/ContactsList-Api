package com.ziran.addresslist.controller;

import com.alibaba.fastjson.JSONObject;
import com.ziran.addresslist.entity.Contacts;
import com.ziran.addresslist.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactsController {
    @Autowired
    private ContactsService contactsService;

    @PostMapping("/add")
    public JSONObject addContact(@RequestBody Contacts newContact){
        boolean res = contactsService.addContact(newContact);
        JSONObject result = new JSONObject();
        if(res) {
            result.put("code", 200);
            result.put("msg", "添加成功！");
        } else {
            result.put("code", 400);
            result.put("msg", "添加失败");
        }
        return result;
    }

    @GetMapping("get/{contactId}")
    public JSONObject getContactById(@PathVariable("contactId") Integer contactId) {
        JSONObject result = new JSONObject();
        try {
            Contacts contacts = contactsService.getContactById(contactId);
            result.put("code", 200);
            result.put("msg", "查询成功");
            result.put("data", contacts);
            return result;
        } catch (Exception e) {
            result.put("code", 400);
            result.put("msg", "查询失败！");
            return result;
        }
    }

    @GetMapping("/getAll/{userId}")
    public JSONObject getAllContact(@PathVariable("userId") Integer userId) {
        System.out.println("userId" + userId);
        JSONObject result = new JSONObject();
        try {
            List<Contacts> contacts = contactsService.getAllContact(userId);
            result.put("code", 200);
            result.put("msg", "查询成功");
            result.put("data", contacts);
            return result;
        } catch (Exception e) {
            result.put("code", 400);
            result.put("msg", "查询失败！");
            return result;
        }
    }

    @PutMapping("/update")
    public JSONObject updateContact(@RequestBody Contacts newContact) {
        System.out.println("update mapping");
        JSONObject result = new JSONObject();
        try {
            Integer res = contactsService.updateContact(newContact);
            int code = 200;
            String msg = "修改成功!";

            switch (res) {
                case 0:
                    code = 400;
                    msg = "修改失败";
                    break;
                case 1:
                    code = 400;
                    msg = "要修改的通讯录信息不存在";
                    break;
            }
            result.put("code", code);
            result.put("msg", msg);
            return result;
        } catch(Exception e) {
            result.put("code", 400);
            result.put("msg", "修改失败！");
            return result;
        }
    }

    @DeleteMapping("/delete/{userId}/{contactId}")
    public JSONObject deleteContact(@PathVariable("userId") Integer userId, @PathVariable("contactId") Integer contactId) {
        boolean res = contactsService.deleteContact(userId, contactId);
        JSONObject result = new JSONObject();
        if(res) {
            result.put("code", 200);
            result.put("msg", "删除成功！");
        } else {
            result.put("code", 400);
            result.put("msg", "删除失败");
        }
        return result;
    }

    @GetMapping("/add/{userId}/{contactId}")
    public JSONObject collectContact(@PathVariable("userId") Integer userId, @PathVariable("contactId") Integer contactId) {
        boolean res = contactsService.collectContact(userId, contactId);
        JSONObject result = new JSONObject();
        if(res) {
            result.put("code", 200);
            result.put("msg", "添加成功！");
        } else {
            result.put("code", 400);
            result.put("msg", "添加失败");
        }
        return result;
    }


}
