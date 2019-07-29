package com.dyhospital.cloudhis.web.service;

import com.dyhospital.cloudhis.message.api.bo.User;
import com.dyhospital.cloudhis.message.api.bo.UserSearchModel;
import com.github.pagehelper.PageInfo;

/**
 * Description:用户
 * User: zhouzhou
 * Date: 2019-07-28
 * Time: 18:18
 */
public interface IApiUserService {

    /**
     * 插入用户
     * @param user
     * @return
     */
    User insert(User user);

    /**
     * 通过id查用户
     * @param id
     * @return
     */
    User selectByPrimaryKey(String id);

    /**
     * 通过id 更新用户信息
     * @param id
     * @param user
     * @return
     */
    User updateByPrimaryKey(String id, User user);

    /**
     * 条件搜索
     * @param searchModel
     * @return
     */
    PageInfo<User> selectBySearchModel(UserSearchModel searchModel);
}
