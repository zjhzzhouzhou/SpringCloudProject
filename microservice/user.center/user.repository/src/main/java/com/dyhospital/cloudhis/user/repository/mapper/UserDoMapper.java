package com.dyhospital.cloudhis.user.repository.mapper;

import com.dyhospital.cloudhis.message.api.bo.UserSearchModel;
import com.dyhospital.cloudhis.user.repository.domain.UserDo;
import com.dyhospital.cloudhis.user.repository.domain.UserDoExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDoMapper {

    int deleteByPrimaryKey(String userId);

    int insert(UserDo record);

    int insertSelective(UserDo record);

    List<UserDo> selectByExample(UserDoExample example);

    UserDo selectByPrimaryKey(String userId);

    int updateByPrimaryKey(UserDo record);

    List<UserDo> findByConditions(UserSearchModel searchModel);
}