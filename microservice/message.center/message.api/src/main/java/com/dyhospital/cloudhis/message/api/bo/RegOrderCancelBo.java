package com.dyhospital.cloudhis.message.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: yangzongxue
 * @Description:
 * @Date: Create in 上午 10:37 2019/5/15 0015
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description= "挂号订单取消对象")
public class RegOrderCancelBo {
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "服务商id")
    private Long spId;
    @ApiModelProperty(value = "订单编号")
    private Long orderNo;
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "取消订单原因")
    private String cause;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "用户姓名")
    private String userName;
    @ApiModelProperty(value = "门店名称")
    private String hosName;
    @ApiModelProperty(value = "科室名称")
    private String deptName;
    @ApiModelProperty(value = "医生名称")
    private String docName;
    @ApiModelProperty(value = "候诊时间")
    private Date medDate;
    @ApiModelProperty(value = "上下午标识")
    private String ampm;
}
