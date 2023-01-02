package com.ziran.addresslist.service;

import com.ziran.addresslist.entity.Contacts;
import com.ziran.addresslist.entity.Users;
import com.ziran.addresslist.mapper.ContactsMapper;
import com.ziran.addresslist.mapper.LoginMapper;
import com.ziran.addresslist.mapper.UserinfoMapper;
import com.ziran.addresslist.mapper.UsersMapper;
import com.ziran.addresslist.model.LoginData;
import common.BcryptUtil;
import common.Constant;
import common.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;


@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private UserinfoMapper userinfoMapper;

    private TokenUtil tokenUtil = new TokenUtil();
    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
//    public JSONObject register(Users user) {
//        JSONObject result = new JSONObject();
//
//        try {
//            Users existUser = usersMapper.getUserByName(user.getUsername());
//            if(existUser != null){
//                //如果用户名已存在
//                result.setMsg("用户名已存在");
//
//            }else{
//                userMapper.regist(user);
//                //System.out.println(user.getId());
//                result.setMsg("注册成功");
//                result.setSuccess(true);
//                result.setDetail(user);
//            }
//        } catch (Exception e) {
//            result.setMsg(e.getMessage());
//            e.printStackTrace();
//        }
//        return result;
//    }
    /**
     * 登录
     * @param loginData 用户名和密码
     * @return JSONObject
     */
    public Users login(LoginData loginData) {
        try {
            String userName = loginData.getUserName();
            String password = loginData.getPassword();

            Users user = usersMapper.getUserByName(userName);
            boolean isPswCorrect = BcryptUtil.match(password, user.getPassword());
            if(user == null || !isPswCorrect){
                return null;
            }
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int register(LoginData userData) {
        try {
            String userName = userData.getUserName();
            String password = userData.getPassword();
            System.out.println("userName" +  userName + "password" + password);
            // 用户名已存在
            Users user = usersMapper.getUserByName(userName);
            if(user != null){
                System.out.println(user.getUserId() + user.getUserName());
                return 1;
            }

            // 注册
            Users newUser = new Users();
            newUser.setUserName(userName);
            newUser.setPassword(BcryptUtil.encode(password));
            loginMapper.register(newUser);

            Integer userId = newUser.getUserId();
            Contacts userinfo = new Contacts();
            userinfo.setUserId(userId);
            userinfo.setName(newUser.getUserName());
            contactsMapper.addContact(userinfo);

            Integer contactId = userinfo.getContactId();
            userinfoMapper.addUserInfo(userId, contactId);
            return 2;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}