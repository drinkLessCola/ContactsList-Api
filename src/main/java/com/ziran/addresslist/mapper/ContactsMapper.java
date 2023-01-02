package com.ziran.addresslist.mapper;

import com.ziran.addresslist.entity.Contacts;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContactsMapper {

    /**
     * 获取某个用户的所有通讯录信息
     * @param userId
     * @return
     */
    List<Contacts> getContactByUserId(Integer userId);

    /**
     * 根据 id 获取某个通讯录信息
     * @param contactId
     * @return
     */
    Contacts getContactById(Integer contactId);

    /**
     * 新增通讯录信息
     * @param mapper
     * @return
     */
    boolean addContact(Contacts mapper);

    /**
     * 删除某个通讯录信息
     * @param userId
     * @param contactId
     */
    boolean deleteContact(Integer userId, Integer contactId);

    /**
     * 更新通讯录信息
     * @param newContact
     */
    boolean updateContact(Contacts newContact);
}
