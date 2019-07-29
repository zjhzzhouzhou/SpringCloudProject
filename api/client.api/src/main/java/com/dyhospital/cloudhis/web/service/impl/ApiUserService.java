package com.dyhospital.cloudhis.web.service.impl;

import com.dyhospital.cloudhis.common.utils.BeanUtils;
import com.dyhospital.cloudhis.common.web.annotation.method.GenericResponse;
import com.dyhospital.cloudhis.message.api.bo.User;
import com.dyhospital.cloudhis.message.api.bo.UserSearchModel;
import com.dyhospital.cloudhis.message.api.client.feign.CustomerUserService;
import com.dyhospital.cloudhis.message.api.dto.user.UserInsertRequest;
import com.dyhospital.cloudhis.message.api.dto.user.UserUpdateRequest;
import com.dyhospital.cloudhis.web.service.IApiUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Description: 用户业务类
 * User: zhouzhou
 * Date: 2019-07-28
 * Time: 19:03
 */
@Service
public class ApiUserService implements IApiUserService {

    @Autowired
    private CustomerUserService customerUserService;

    @Override
    public User insert(User user) {
        UserInsertRequest request = new UserInsertRequest();
        BeanUtils.copyProperties(user, request);
        GenericResponse<User> response = customerUserService.insertUser(request);
        GenericResponse.assertResponse(response);
        return response.getResult();
    }

    @Override
    public User selectByPrimaryKey(String id) {
        GenericResponse<User> response = customerUserService.findById(id);
        GenericResponse.assertResponse(response);
        return response.getResult();
    }

    @Override
    public User updateByPrimaryKey(String id, User user) {
        UserUpdateRequest request = new UserUpdateRequest();
        BeanUtils.copyProperties(user, request);
        GenericResponse<User> response = customerUserService.updateById(id, request);
        GenericResponse.assertResponse(response);
        return response.getResult();
    }

    @Override
    public PageInfo<User> selectBySearchModel(UserSearchModel searchModel) {
        Map<String, Object> params = BeanUtils.toMap(searchModel);
        GenericResponse<PageInfo<User>> response = customerUserService.findBySearchModel(params);
        GenericResponse.assertResponse(response);
        return response.getResult();
    }
}
