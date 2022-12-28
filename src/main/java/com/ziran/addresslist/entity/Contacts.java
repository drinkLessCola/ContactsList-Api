package com.ziran.addresslist.entity;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

@TableName("contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacts implements Serializable {

    @NotNull(message="[]不能为空")
    @TableId("contact_id")
    private Integer contactId;

    @TableField("user_id")
    private Integer userId;

    @TableField("name")
    @Size(max= 30)
    private String name;
    /**
    * 
    */
    @Size(max= 11)
    @TableField("mobile")
    private String mobile;
    /**
    * 
    */
    @Size(max= 13)
    @TableField("homeTel")
    private String homeTel;
    /**
    * 
    */
    @Size(max= 13)
    @TableField("workTel")
    private String workTel;
    /**
    * 
    */
    @Size(max= 100)
    @TableField("companyAddress")
    private String companyAddress;
    /**
    * 
    */
    @TableField("birthday")
    private Date birthday;
    /**
    * 
    */
    @Size(max= 200)
    @TableField("remark")
    private String remark;
}
