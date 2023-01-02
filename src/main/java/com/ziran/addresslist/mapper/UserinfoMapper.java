package com.ziran.addresslist.mapper;

import com.alibaba.fastjson.JSONObject;
import com.ziran.addresslist.entity.Contacts;
import com.ziran.addresslist.entity.Userinfo;
import com.ziran.addresslist.model.UserInfoData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface UserinfoMapper {
    Contacts getUserInfo(Integer userId);

    boolean addUserInfo(int userId, int contactId);

    boolean updateUserInfo(Contacts newContact);
}
