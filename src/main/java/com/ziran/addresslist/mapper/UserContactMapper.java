package com.ziran.addresslist.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserContactMapper {

    boolean addUserContact(Integer userId, Integer contactId);
}
