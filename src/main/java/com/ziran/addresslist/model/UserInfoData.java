package com.ziran.addresslist.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoData {
    private String name;
    private String mobile;
    private String homeTel;
    private String workTel;
    private String companyAddress;
    private Date birthday;
    private String remark;
}
