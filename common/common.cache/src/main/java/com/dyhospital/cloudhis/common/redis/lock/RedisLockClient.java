package com.dyhospital.cloudhis.common.redis.lock;


import com.dyhospital.cloudhis.common.redis.cluster.JedisClusterCache;
import com.dyhospital.cloudhis.common.web.constants.BaseResultCode;
import com.dyhospital.cloudhis.common.web.exception.reg.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @program: yyzlpt_code
 * @description: 获取redis锁
 * @author: linhao
 * @create: 2018/12/29 14:24
 **/
@Component
@Slf4j
public class RedisLockClient {

    @Autowired
    private JedisClusterCache jedisClusterCache;
    /**
     * 成功结果比对值
     */
    private static final String LOCK_SUCCESS = "OK";
    /**
     * key不存在设置成功
     */
    private static final String SET_IF_NOT_EXIST = "NX";
    /**
     * 毫秒
     */
    private static final String SET_WITH_EXPIRE_TIME = "PX";


    /**
     * @param key
     * @param lockTime 锁时间  ms
     * @Description: 获取分布式锁
     * @return: void
     * @Author: linhao
     * @Date: 2019/1/3
     */
    public void getLock(String key, long lockTime) {
        if (StringUtils.isEmpty(key) || lockTime <= 0) {
            throw new BizException(BaseResultCode.GET_REDIS_LOCK_ERROR);
        }
        if (!setNx(key, UUID.randomUUID().toString(), SET_WITH_EXPIRE_TIME, lockTime)) {
            throw new BizException(BaseResultCode.GET_REDIS_LOCK_ERROR);
        }
    }

    /**
     * @param key
     * @param lockTime 锁时间  ms
     * @Description: 获取分布式锁
     * @return: void
     * @Author: linhao
     * @Date: 2019/1/3
     */
    public Boolean getRedisLock(String key, long lockTime) {
        if (StringUtils.isEmpty(key) || lockTime <= 0) {
            throw new BizException(BaseResultCode.GET_REDIS_LOCK_ERROR);
        }
        if (!setNx(key, UUID.randomUUID().toString(), SET_WITH_EXPIRE_TIME, lockTime)) {
            return false;
        }else {
            return true;
        }
    }
    /**
     * @param key
     * @param value
     * @param expx
     * @param time
     * @Description: redis setNx  key不存在才能set成功，若key存在则返回null
     * @return: com.dyhospital.zjguahao.common.redis.lock.RedisLock
     * @Author: linhao
     * @Date: 2018/12/29
     */
    private Boolean setNx(String key, Object value, String expx, long time) {

        if (log.isDebugEnabled()) {
            log.debug("RedisLockClient.setNx key : {} ,value : {} ,expx : {} , time : {}", key, value.toString(), expx, time);
        }
        String result = jedisClusterCache.set(key, value, SET_IF_NOT_EXIST, expx, time);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * @param key
     * @param lockTime 锁时间  ms
     * @param waitTime 等待时间  ms
     * @Description: 获取redis锁，有等待时间
     * @return: void
     * @Author: linhao
     * @Date: 2019/1/3
     */
    public void tryLock(String key, long lockTime, long waitTime) {
        if (StringUtils.isEmpty(key) || lockTime <= 0 || waitTime <= 0) {
            throw new BizException(BaseResultCode.GET_REDIS_LOCK_ERROR);
        }
        if (!setNx(key, UUID.randomUUID().toString(), SET_WITH_EXPIRE_TIME, lockTime)) {
            try {
                Thread.sleep(waitTime);
                if (!setNx(key, UUID.randomUUID().toString(), SET_WITH_EXPIRE_TIME, lockTime)) {
                    throw new BizException(BaseResultCode.GET_REDIS_LOCK_ERROR);
                }
            } catch (InterruptedException e) {
                log.error("RedisLockClient.tryLock error  key : {} ,lockTime : {} ,waitTime : {}", key, lockTime, waitTime, e);
                throw new BizException(BaseResultCode.GET_REDIS_LOCK_ERROR);
            }
        }
    }

    /**
     * @param key
     * @Description: 释放redis锁
     * @return: void
     * @Author: linhao
     * @Date: 2019/1/3
     */
    public void unLock(String key) {
        if (StringUtils.isNotEmpty(key)) {
            try {
                jedisClusterCache.del(key);
            } catch (Exception e) {
                log.error("unLock is failed",e);
            }
        }
    }
}
