package com.dyhospital.cloudhis.user.controller;

import com.dyhospital.cloudhis.common.utils.BeanUtils;
import com.dyhospital.cloudhis.common.web.annotation.method.GenericResponse;
import com.dyhospital.cloudhis.common.web.exception.reg.exception.BizErrorCodeEnum;
import com.dyhospital.cloudhis.common.web.exception.reg.exception.BizException;
import com.dyhospital.cloudhis.message.api.bo.User;
import com.dyhospital.cloudhis.message.api.bo.UserSearchModel;
import com.dyhospital.cloudhis.message.api.client.UserApi;
import com.dyhospital.cloudhis.message.api.dto.user.UserInsertRequest;
import com.dyhospital.cloudhis.message.api.dto.user.UserUpdateRequest;
import com.dyhospital.cloudhis.user.service.IUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:用户 ctl
 * <p>
 * User: zhouzhou
 * Date: 2019-07-08
 * Time: 18:57
 */
@Api("用户")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "插入用户")
    @PostMapping(value = UserApi.INSERT_USER, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse<User> insertUser(@RequestBody UserInsertRequest request) {
      
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return new GenericResponse<>(userService.insert(user));
    }

    @ApiOperation(value = "通过id查询用户")
    @GetMapping(value = UserApi.QUERY_USER_BY_ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse<User> findById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            throw new BizException(BizErrorCodeEnum.ID_IS_NULL);
        }
        return new GenericResponse<>(userService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "根据id更新用户")
    @PostMapping(value = UserApi.UPDATE_USER_BY_ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse<User> updateById(@PathVariable("id") String id, @RequestBody UserUpdateRequest request) {
        if (StringUtils.isEmpty(id)) {
            throw new BizException(BizErrorCodeEnum.ID_IS_NULL);
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return new GenericResponse<>(userService.updateByPrimaryKey(id, user));
    }

    @ApiOperation(value = "根据搜索条件分页搜索用户")
    @GetMapping(value = UserApi.QUERY_USER_BY_SEARCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse<PageInfo<User>> findBySearchModel(UserSearchModel searchModel) {
        if (searchModel == null) {
            throw new BizException(BizErrorCodeEnum.SEARCH_IS_NULL);
        }
        return new GenericResponse<>(userService.selectBySearchModel(searchModel));
    }



}
