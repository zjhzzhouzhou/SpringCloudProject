package com.dyhospital.cloudhis.message.api.client.feign;

import com.dyhospital.cloudhis.common.web.annotation.method.GenericResponse;
import com.dyhospital.cloudhis.common.web.constants.BaseResultCode;
import com.dyhospital.cloudhis.message.api.dto.SendMessageSmsRequest;
import com.dyhospital.cloudhis.message.api.dto.Student;
import org.springframework.stereotype.Component;

/**
 * Description:Message熔断器
 * User: zhouzhou
 * Date: 2019-05-10
 * Time: 6:17 PM
 */
@Component
public class CustomerMessageServiceHystrix implements CustomerMessageService {

    @Override
    public GenericResponse<Boolean> publishMessage(Integer counts) {
        return getGenericResponse();
    }

    @Override
    public String getStrValue(String key) {
        return "熔断";
    }

    @Override
    public String putStrValue(String key) {
        return "熔断";
    }

    @Override
    public Object getStuValue(Student student) {
        return getGenericResponse();
    }

    @Override
    public Object updateStuValue(Student student) {
        return getGenericResponse();
    }


    private GenericResponse getGenericResponse(){
        GenericResponse<Boolean> response = new GenericResponse<>();
        response.setCode(BaseResultCode.UNKOWN_ERROR);
        response.setMessage("服务调用熔断");
        return response;
    }
}
