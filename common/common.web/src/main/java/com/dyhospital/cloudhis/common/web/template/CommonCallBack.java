package com.dyhospital.zjguahao.common.web.template;

/**
 * Description: 通用回调类
 * <p>
 *     基础功能: 1.校验参数有效性
 *               2.执行主流程
 * </p>
 * User: zhouzhou
 * Date: 2018-08-20
 * Time: 9:58
 */
public interface CommonCallBack {

    void checkParms();

    void execute();
}
