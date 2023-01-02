package com.ziran.addresslist.service;

import com.alibaba.fastjson.JSONObject;
import com.ziran.addresslist.entity.Contacts;
import com.ziran.addresslist.entity.Userinfo;
import com.ziran.addresslist.mapper.ContactsMapper;
import com.ziran.addresslist.mapper.UserinfoMapper;
import com.ziran.addresslist.model.UserInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Autowired
    private ContactsMapper contactsMapper;

    public Contacts getUserInfo(Integer userId) {
        return userinfoMapper.getUserInfo(userId);
    }

    public boolean addUserInfo(Contacts newContact) {
        try {
            boolean res = contactsMapper.addContact(newContact);
            if(!res) return false;

            int contactId = newContact.getContactId();
            int userId = newContact.getUserId();
            System.out.println("user_id" + userId);
            System.out.println("contact_id" + contactId);
            res = userinfoMapper.addUserInfo(userId, contactId);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserInfo(Contacts newContact) {
        try {
            return userinfoMapper.updateUserInfo(newContact);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
