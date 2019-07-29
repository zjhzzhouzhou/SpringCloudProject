package com.dyhospital.cloudhis.common.web.template.tccTemplate;


import com.dyhospital.cloudhis.common.web.exception.reg.exception.BizErrorCodeEnum;
import com.dyhospital.cloudhis.common.web.exception.reg.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Description: 分布式事务执行模板
 * <p>
 * 1. 先执行各个服务块业务
 * 2. 执行结束通过confirm判定执行结果, 如果失败则进行取消(回滚操作)
 * 3. 如果执行服务模块发生异常,则判定后进行
 * </p>
 * User: zhouzhou
 * Date: 2018-08-27
 * Time: 10:27
 */
public class TccTemplate {

    private static final Logger logger = LoggerFactory.getLogger(TccCallBack.class);

    /**
     * 分布式事务模板
     *
     * @param tccCallBack 分布式事务执行回调
     * @param method      当前方法名(封装参数, 可方便捞取数据)
     */
    public static TccResult process(TccCallBack tccCallBack, String method) {
        // 返回一个消息用于
        TccResult tccResult = new TccResult();
        String msg = "";
        try {
            // 执行主业务
            tccCallBack.tryExecute();
            // 进行确认执行结果,如果结果是false,则执行回滚操作
            boolean confirm = tccCallBack.confirm();
            if (confirm) {
                tccResult.setStatus(true);
                msg = String.format("分布式事务{%s}执行成功", method);
                logger.info(msg);
            } else {
                tccResult.setStatus(false);
                msg = String.format("分布式事务{%s}执行失败,进行回滚操作", method);
                logger.warn(msg);
                tccCallBack.cancel();
            }
        } catch (BizException e) {
            // 主流程发生异常, 则直接执行回滚操作
            tccResult.setStatus(false);
            tccResult.setCode(e.getErrorCode().getCode());
            msg = e.getMessage();
            logger.warn(String.format("分布式事务{%s}执行发生异常,进行回滚操作", method), e);
            tccCallBack.cancel();
            throw e;
        } catch (Exception e) {
            // 主流程发生异常, 则直接执行回滚操作
            tccResult.setCode(BizErrorCodeEnum.UNSPECIFIED.getCode());
            tccResult.setStatus(false);
            msg = e.getMessage();
            logger.warn(String.format("分布式事务{%s}执行发生异常,进行回滚操作", method), e);
            tccCallBack.cancel();
            throw e;
        } finally {
            // 返回结果Result
            tccResult.setMsg(msg);
            return tccResult;
        }


    }
}
