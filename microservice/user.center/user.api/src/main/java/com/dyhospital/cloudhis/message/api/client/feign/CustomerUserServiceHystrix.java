package com.dyhospital.cloudhis.message.api.client.feign;

import com.dyhospital.cloudhis.common.web.annotation.method.GenericResponse;
import com.dyhospital.cloudhis.common.web.constants.BaseResultCode;
import com.dyhospital.cloudhis.message.api.bo.User;
import com.dyhospital.cloudhis.message.api.bo.UserSearchModel;
import com.dyhospital.cloudhis.message.api.dto.user.UserInsertRequest;
import com.dyhospital.cloudhis.message.api.dto.user.UserUpdateRequest;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:User熔断器
 * User: zhouzhou
 * Date: 2019-05-10
 * Time: 6:17 PM
 */
@Component
public class CustomerUserServiceHystrix implements CustomerUserService {


    private GenericResponse getGenericResponse() {
        GenericResponse<Boolean> response = new GenericResponse<>();
        response.setCode(BaseResultCode.UNKOWN_ERROR);
        response.setMessage("用户服务调用熔断");
        return response;
    }

    @Override
    public GenericResponse<User> insertUser(UserInsertRequest request) {
        return getGenericResponse();
    }

    @Override
    public GenericResponse<User> findById(String id) {
        return getGenericResponse();
    }

    @Override
    public GenericResponse<User> updateById(String id, UserUpdateRequest request) {
        return getGenericResponse();
    }

    @Override
    public GenericResponse<PageInfo<User>> findBySearchModel(Map<String, Object> searchModel) {
        return getGenericResponse();
    }
}
