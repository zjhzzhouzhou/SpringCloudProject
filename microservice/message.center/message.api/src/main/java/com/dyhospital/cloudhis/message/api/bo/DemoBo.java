package com.dyhospital.cloudhis.message.api.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author zhouzhou
 * @date 2019-7-01 16:48:06
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DemoBo {

    private Long id;

    private String accessName;

    private String userName;

    private String describe;

    private String mobileNo;

    private String eMail;

    private String password;

    private String passwordEnable;

    private String remark;

    private String enable;

    private Date createTime;

    private Date updateTime;

    private Long operateUser;
}