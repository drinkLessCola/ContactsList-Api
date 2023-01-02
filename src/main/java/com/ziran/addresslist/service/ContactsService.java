package com.ziran.addresslist.service;

import com.ziran.addresslist.entity.Contacts;
import com.ziran.addresslist.mapper.ContactsMapper;
import com.ziran.addresslist.mapper.UserContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContactsService {
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private UserContactMapper userContactMapper;


    public List<Contacts> getContactByUserId(Integer userId) {
        return contactsMapper.getContactByUserId(userId);
    }

    public boolean addContact(Contacts newContact) {
        try{
            boolean res = contactsMapper.addContact(newContact);
            if(!res) return false;

            Integer contactId = newContact.getContactId();
            Integer userId = newContact.getUserId();
            res = userContactMapper.addUserContact(userId, contactId);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer updateContact(Contacts newContact) {
        try{
            Integer contactId = newContact.getContactId();
            Integer userId = newContact.getUserId();
            if(contactId == null || userId == null) return 0;
            Contacts oldContact = contactsMapper.getContactById(contactId);
            if(oldContact == null || oldContact.getUserId() != userId) return 1;
            contactsMapper.updateContact(newContact);
            return 200;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Contacts> getAllContact(Integer userId) {
        return contactsMapper.getContactByUserId(userId);
    }

    public boolean deleteContact(Integer userId, Integer contactId) {
        try{
            Contacts contact = contactsMapper.getContactById(contactId);
            if(contact == null || contact.getUserId() != userId) return false;
            contactsMapper.deleteContact(userId, contactId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean collectContact(Integer userId, Integer contactId) {
        try{
            Contacts contact = contactsMapper.getContactById(contactId);
            if(contact == null || contact.getUserId() == userId) return false;
            userContactMapper.addUserContact(userId, contactId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Contacts getContactById(Integer contactId) {
        return contactsMapper.getContactById(contactId);
    }
}
