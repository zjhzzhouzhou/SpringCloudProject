package com.dyhospital.cloudhis.common.web.exception.reg.exception;

/**
 * Description:
 * User: zhouzhou
 * Date: 2019-01-08
 * Time: 14:50
 */

import org.apache.commons.lang.StringUtils;

/**
 * 业务错误码
 * @author jacques
 * @version $Id: BusinessErrorCodeEnum.java, v 0.1 2016年4月28日 下午5:44:00 jacques Exp $
 */
public enum BizErrorCodeEnum implements ErrorCode {

    /** 未指明的异常 */
    UNSPECIFIED("500", "网络异常，请稍后再试"),
    NO_SERVICE("404", "网络异常, 服务器熔断"),

    // 通用异常
    REQUEST_ERROR("400", "入参异常,请检查入参后再次调用"),
    PAGE_NUM_IS_NULL("4001","页码不能为空"),
    PAGE_SIZE_IS_NULL("4002","页数不能为空"),
    ID_IS_NULL("4003","ID不能为空"),
    SEARCH_IS_NULL("4004","搜索条件不能为空"),

    // 用户挂号验证
    USER_AUTH_FAIL("10001", "用户鉴权失败"),
    USER_IS_ABNORMAL("10002","用户状态不正常"),
    USER_REALNAME("10003","用户未实名"),
    USER_NAME_EMPTY("10003","用户名为空"),
    USER_UNFILE("10004","用户未建档"),
    USER_REG_RATE("10005","用户挂号频率太快,一周三次"),
    USER_CANCEL_TIME("10006","一天取消3次订单,不能挂号"),
    DEPT_LIMIT_AGE("10007","年龄不符合挂号科室"),
    DEPT_LIMIT_SEX("10008","性别不符合挂号科室"),
    USER_CANCEL_WEEK_TIME("10009","一周取消5次订单,不能挂号"),
    SLIDE_CAPTCHA_ERROR("10010","滑动验证码错误"),
    USER_REGING("10011","当前用户正在挂号，请稍后再试"),



    // 订单相关
    LOCK_ORDER_INIT("20001","锁号成功,订单初始化失败"),
    REG_ORDER_NO_IS_NULL("20002","订单编号为空"),
    ORDER_CANCELLED("20003","订单已取消"),
    REG_ORDER_STATUS_CHANGED("20004","订单状态错误"),
    SYN_ORDER_FAIL("20006","同步订单失败"),
    UPDATE_ORDER_FAIL("20006","同步订单失败"),
    INTERFACE_LINK_NON_STANDARD("20007","接口调用不规范"),
    REG_ORDER_NON_EXIST("20008","订单不存在"),
    UNABLE_CERTNUM("20009","获取不到用户身份证"),
    DUPLICATE_REG_ERROR("20010","同一医院同一科室不能重复挂号"),
    PATIENT_ID_IS_NULL("20011","门诊号不能为空"),
    HOS_NAME_IS_NULL("20012","医院名称不能为空"),
    DEPT_ID_IS_NULL("20013","科室id不能为空"),
    DOCTOR_ID_IS_NULL("20014","医生id不能为空"),
    AMPM_IS_NULL("20015","上下午标识不能为空"),
    NUMBER_ID_IS_NULL("20016","号源id不能为空"),
    SCHEDULE_ID_IS_NULL("20017","排班id不能为空"),
    ORG_ID_IS_NULL("20018","医院编号不能为空"),
    SP_ID_IS_NULL("20019","服务商id不能为空"),
    STORE_ID_IS_NULL("20020","门店id不能为空"),
    REG_MODE_IS_NULL("20021","订单类型不能为空"),
    MED_DATE_IS_NULL("20022","候诊时间不能为空"),
    REG_FEE_IS_NULL("20023","挂号费不能为空"),
    TREAT_FEE_IS_NULL("20024","诊疗费不能为空"),
    DOC_NAME_IS_NULL("20025","医生名称不能为空"),
    REG_AMOUNT_IS_NULL("20026","挂号金额不能为空"),
    REG_TYPE_IS_NULL("20027","挂号类型不能为空"),
    CANCEL_CAUSE_IS_NULL("20029","订单取消原因不能为空"),
    AMPM_PARAM_ERROR("20030","上下午标识传入错误"),
    REG_MODE_PARAM_ERROR("20031","订单类型传入错误"),
    REG_TYPE_PARAM_ERROR("20032","挂号类型传入错误"),
    REG_CANCEL_ORDER_NON_EXIST("20033","取消订单不存在"),
    ORDER_NO_ALEADY_EXISTS("20034","商品订单号已存在"),
    DEL_ORDER_CANCEL_FAIL("20035","删除订单取消失败"),
    DEPT_NAME_IS_NULL("20036","科室名称不能为空"),
    USER_CANCEL_OUT_OF_TIME("20037","由于一天取消3次订单,不能执行此操作"),
    USER_CANCEL_OUT_OF_WEEK_TIME("20038","由于一周取消5次订单,不能执行此操作"),
    INSERT_CANCEL_REG_ORDER_FAIL("20039","取消挂号订单记录创建失败"),
    HOS_ID_IS_NULL("200340","院区id不能为空"),
    PARENT_DEPT_ID_IS_NULL("200341","父科室id不能为空"),


    // 短信相关
    SEND_MASSAGE_FAIL("30001","发送短消息失败"),
    SEND_MASSAGE_OFTEN("30002","操作发送短消息太频繁,请稍后再试"),
    MESSAGE_TEMPLATE_UNDEFINED("30003","短信模板未定义"),

    //支付相关
    CREATE_PAY_ORDER_FAIL("40001","创建订单支付失败"),
    UPDATE_PAY_ORDER_FAIL("40002","更新支付订单失败"),
    DEL_PAY_ORDER_FAIL("40003","更新支付订单失败"),
    PAY_ORDER_NO_EXISTS("40004","支付订单不存在"),
    REFUND_APPLY_NO_EXISTS("40005","退款申请不存在"),
    VERIFY_NOT_PASS("40006","验签"),
    RES_FAIL("40007","响应失败"),
    PAY_CHANNEL_IS_NULL("40008","支付渠道不能为空"),
    PAY_CHANNEL_PARAM_ERROR("40009","支付订单渠道参数错误"),



    ;

    /** 错误码 */
    private final String code;

    /** 描述 */
    private final String description;

    /**
     * @param code 错误码
     * @param description 描述
     */
    private BizErrorCodeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code 编码。
     * @return 枚举。
     */
    public static BizErrorCodeEnum getByCode(String code) {
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return value;
            }
        }
        return UNSPECIFIED;
    }

    /**
     * 枚举是否包含此code
     * @param code 枚举code
     * @return 结果
     */
    public static Boolean contains(String code){
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return true;
            }
        }
        return  false;
    }
    
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}