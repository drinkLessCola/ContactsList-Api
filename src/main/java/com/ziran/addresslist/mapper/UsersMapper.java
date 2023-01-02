package com.ziran.addresslist.mapper;

import com.ziran.addresslist.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UsersMapper{
    Users getUserById(Integer userId);

    Users getUserByName(String userName);

    List<Users> getAllUsers();

    List<Users> searchUsers(String keyword);
}
