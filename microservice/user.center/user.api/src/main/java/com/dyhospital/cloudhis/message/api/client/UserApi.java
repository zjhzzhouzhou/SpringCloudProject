package com.dyhospital.cloudhis.message.api.client;

/**
 * @author zhouzhou
 * @date 2019-7-01 16:52:38
 * 服务列表
 */
public interface UserApi {


    String APPLICATION_NAME = "user-service";


    // ------------------------ 对账详情 ------------------------------

    // 插入对账详情
    String INSERT_USER= "/pay/insertUser";

    // 通过id查询对账详情
    String QUERY_USER_BY_ID = "/pay/queryUserById/{id}";

    // 通过id更新对账详情
    String UPDATE_USER_BY_ID = "/pay/updateUserById/{id}";

    // 通过搜索类查询对账详情
    String QUERY_USER_BY_SEARCH = "/pay/queryUserBySearchModel";



}
