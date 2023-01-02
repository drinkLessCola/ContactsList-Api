package com.ziran.addresslist.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziran.addresslist.entity.Contacts;
import com.ziran.addresslist.entity.Users;
import com.ziran.addresslist.mapper.ContactsMapper;
import com.ziran.addresslist.mapper.UserContactMapper;
import com.ziran.addresslist.mapper.UserinfoMapper;
import com.ziran.addresslist.mapper.UsersMapper;
import com.ziran.addresslist.model.UserInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService{
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserinfoMapper userinfoMapper;

    public List<Contacts> findUser(String keyword) {
        List<Users> usersList = usersMapper.searchUsers(keyword);
        ArrayList<Contacts> res = new ArrayList<>();
        for(Users u : usersList) {
            res.add(userinfoMapper.getUserInfo(u.getUserId()));
        }
        return res;
    }

    public Users getUserById(Integer userId) {
        return usersMapper.getUserById(userId);
    }

    public Users getUserByName(String userName) {
        return usersMapper.getUserByName(userName);
    }

    public List<Users> getAllUsers() {
        return usersMapper.getAllUsers();
    }

}
