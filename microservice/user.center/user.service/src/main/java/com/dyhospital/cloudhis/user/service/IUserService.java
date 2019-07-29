package com.dyhospital.cloudhis.user.service;

import com.dyhospital.cloudhis.message.api.bo.User;
import com.dyhospital.cloudhis.message.api.bo.UserSearchModel;
import com.github.pagehelper.PageInfo;

/**
 * Description:支付对账详情service
 * User: zhouzhou
 * Date: 2019-07-08
 * Time: 16:52
 */
public interface IUserService {

    /**
     * 通过批次id 搜索支付对账详情
     * @param id
     * @return
     */
    User selectByPrimaryKey(String id);

    /**
     * 插入支付对账详情记录
     * @param record
     * @return
     */
    User insert(User record);


    /**
     * 通过批次id ,更新支付对账详情
     * @param id
     * @param record
     * @return
     */
    User updateByPrimaryKey(String id, User record);


    /**
     * 通过条件分页检索支付对账详情
     * @param searchModel
     * @return
     */
    PageInfo<User> selectBySearchModel(UserSearchModel searchModel);


}
