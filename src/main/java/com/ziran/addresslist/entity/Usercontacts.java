package com.ziran.addresslist.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

import java.io.Serializable;


@Data
@TableName("usercontacts")
public class Usercontacts implements Serializable {

    /** 所属用户 id */
    @NotNull(message="[]不能为空")
    @TableId("user_id")
    private Integer userId;

    /** 存储的通讯信息 */
    @NotNull(message="[]不能为空")
    @TableId("contact_id")
    private Integer contactId;
}
