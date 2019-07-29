package com.dyhospital.cloudhis.message.api.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description:
 * User: zhouzhou
 * Date: 2019-07-28
 * Time: 12:24
 */
@Data
@ApiModel("用户更新请求")
public class UserUpdateRequest {

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "生日", position = 1)
    private Date birthday;

    @ApiModelProperty(value = "证件号码", position = 2)
    private String certNum;

    @ApiModelProperty(value = "证件类型", position = 3)
    private String certType;

    @ApiModelProperty(value = "真实姓名", position = 4)
    private String realName;

    @ApiModelProperty(value = "性别", position = 5)
    private String sex;

    @ApiModelProperty(value = "手机号", position = 6)
    private String mobile;

    @ApiModelProperty(value = "手机号码", position = 7)
    private String phone;

    @ApiModelProperty(value = "邮箱", position = 8)
    private String email;

    @ApiModelProperty(value = "地址", position = 9)
    private String address;

    @ApiModelProperty(value = "状态", position = 10)
    private String status;

    @ApiModelProperty(value = "备注", position = 11)
    private String remark;
}
