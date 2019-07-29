package com.dyhospital.cloudhis.message.api.client.feign;

import com.dyhospital.cloudhis.common.web.annotation.method.GenericResponse;
import com.dyhospital.cloudhis.message.api.bo.User;
import com.dyhospital.cloudhis.message.api.bo.UserSearchModel;
import com.dyhospital.cloudhis.message.api.client.UserApi;
import com.dyhospital.cloudhis.message.api.dto.user.UserInsertRequest;
import com.dyhospital.cloudhis.message.api.dto.user.UserUpdateRequest;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 用户
 * User: zhouzhou
 * Date: 2019-05-10
 * Time: 5:04 PM
 */
@Component
@FeignClient(value = UserApi.APPLICATION_NAME, fallback = CustomerUserServiceHystrix.class)
public interface CustomerUserService {

    @ApiOperation(value = "插入用户")
    @PostMapping(value = UserApi.INSERT_USER, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GenericResponse<User> insertUser(@RequestBody UserInsertRequest request);

    @ApiOperation(value = "通过id查询用户")
    @GetMapping(value = UserApi.QUERY_USER_BY_ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GenericResponse<User> findById(@PathVariable("id") String id);

    @ApiOperation(value = "根据id更新用户")
    @PostMapping(value = UserApi.UPDATE_USER_BY_ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GenericResponse<User> updateById(@PathVariable("id") String id, @RequestBody UserUpdateRequest request);

    @ApiOperation(value = "根据搜索条件分页搜索用户")
    @GetMapping(value = UserApi.QUERY_USER_BY_SEARCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GenericResponse<PageInfo<User>> findBySearchModel(@RequestParam Map<String, Object> searchModel);
}