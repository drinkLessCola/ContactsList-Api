package com.ziran.addresslist.mapper;

import com.ziran.addresslist.entity.Users;
import com.ziran.addresslist.model.LoginData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginMapper {
    Users login(String userName, String password);

    void register(Users newUser);
}
