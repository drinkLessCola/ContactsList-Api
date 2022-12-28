package com.ziran.addresslist.entity;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("userinfo")
public class Userinfo implements Serializable {

    /** 用户 id */
    @NotNull(message="[]不能为空")
    @TableId("user_id")
    private Integer userId;

    /** 用户对应的个人档案 */
    @TableField("contact_id")
    private Integer contactId;
}
