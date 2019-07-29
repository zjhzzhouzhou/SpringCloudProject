package com.dyhospital.cloudhis.message.api.bo;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 */
@Data
public class User {

    private String userId;

    private Date birthday;

    private String certNum;

    private String certType;

    private String realName;

    private String sex;

    private String mobile;

    private String phone;

    private String email;

    private String address;

    private String status;

    private Date gmtCreate;

    private Date gmtModified;

    private String remark;

}