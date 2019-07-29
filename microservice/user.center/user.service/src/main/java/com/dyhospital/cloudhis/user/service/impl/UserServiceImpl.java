package com.dyhospital.cloudhis.user.service.impl;

import com.dyhospital.cloudhis.common.utils.BeanUtils;
import com.dyhospital.cloudhis.common.utils.DateUtil;
import com.dyhospital.cloudhis.common.web.exception.reg.exception.BizErrorCodeEnum;
import com.dyhospital.cloudhis.common.web.exception.reg.exception.BizException;
import com.dyhospital.cloudhis.message.api.bo.User;
import com.dyhospital.cloudhis.message.api.bo.UserSearchModel;
import com.dyhospital.cloudhis.user.repository.domain.UserDo;
import com.dyhospital.cloudhis.user.repository.mapper.UserDoMapper;
import com.dyhospital.cloudhis.user.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: 支付对账详情service实现类
 * User: zhouzhou
 * Date: 2019-07-08
 * Time: 18:20
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDoMapper userDoMapper;

    @Override
    public User selectByPrimaryKey(String id) {
        User user = new User();
        UserDo userDo = userDoMapper.selectByPrimaryKey(id);
        if (userDo != null) {
            BeanUtils.copyProperties(userDo, user);
        } else {
            return null;
        }
        return user;
    }

    @Override
    public User insert(User user) {
        String id = DateUtil.formatDate(DateUtil.DATE_TIME_NO_SPACE_MS_PATTERN,new Date());
        // 幂等
        User billInfo = selectByPrimaryKey(id);
        if (billInfo != null) {
            throw new BizException(BizErrorCodeEnum.UNSPECIFIED);
        }

        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(user, userDo);
        userDo.setUserId(id);
        userDoMapper.insert(userDo);
        return selectByPrimaryKey(id);

    }

    @Override
    public User updateByPrimaryKey(String id, User record) {
        User user = selectByPrimaryKey(id);
        if (user != null) {
            UserDo userDo = new UserDo();
            BeanUtils.copyProperties(user, userDo);
            BeanUtils.copyProperties(record, userDo);
            userDoMapper.updateByPrimaryKey(userDo);
            return selectByPrimaryKey(user.getUserId());

        } else {
            throw new BizException(BizErrorCodeEnum.UNSPECIFIED);
        }
    }

    @Override
    public PageInfo<User> selectBySearchModel(UserSearchModel searchModel) {
        // 设置初始页,和每页查找数量
        PageHelper.startPage(searchModel.getStartPage(), searchModel.getPageSize());

        List<UserDo> userDos = userDoMapper.findByConditions(searchModel);
        PageInfo pageInfo = new PageInfo<>(userDos);
        if (CollectionUtils.isEmpty(userDos)) {
            return null;
        } else {
            List<User> list = new ArrayList<>();
            for (UserDo userDo : userDos) {
                User user = new User();
                BeanUtils.copyProperties(userDo, user);
                list.add(user);
            }
            pageInfo.setList(list);

            return pageInfo;
        }
    }


}
