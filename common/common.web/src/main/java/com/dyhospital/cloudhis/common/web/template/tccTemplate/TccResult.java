package com.dyhospital.cloudhis.common.web.template.tccTemplate;

/**
 * Description: 响应结果(暂用于tcc)
 * User: zhouzhou
 * Date: 2018-08-27
 * Time: 11:08
 */

public class TccResult<T> {

    private String code;
    /** 响应数据 */
    private T data;
    /** 响应状态 */
    private Boolean status = true;
    /** 响应消息 */
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
