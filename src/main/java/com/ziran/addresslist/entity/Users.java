package com.ziran.addresslist.entity;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("users")
@Data
public class Users implements Serializable {

    /** 用户 id */
    @NotNull()
    @TableId("user_id")
    private Integer userId;

    /** 是否已删除 */
    @TableField("is_delete")
    private Integer isDelete;

    /** 用户昵称 */
    @Size(max= 30)
    private String name;

    /** 密码（加盐） */
    @Size(max= 100)
    private String password;

    /** 创建时间 */
    private Date createTime;

}
