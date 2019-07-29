package com.dyhospital.cloudhis.common.web.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description:搜索专用
 * User: zhouzhou
 * Date: 2019-07-26
 * Time: 17:28
 */
@Data
public class CommonSearchModel {


    @ApiModelProperty(value = "起始页", position = 0)
    int startPage = 0;

    @ApiModelProperty(value = "每页大小(默认10)", position = 1)
    int pageSize = 10;

    @ApiModelProperty(value = "创建时间-起,yyyy-MM-dd HH:mm:ss", position = 5)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModifiedStart;

    @ApiModelProperty(value = "创建时间-止,yyyy-MM-dd HH:mm:ss",position = 6)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModifiedEnd;


}
