package com.wutuobang.common.utils;

/**
 * Created by majiancheng on 2017/4/25.
 */
public class ResultParam {

    private int code;

    private String message;

    private Object data;

    /**
     * 操作成功
     */
    public static final ResultParam SUCCESS_RESULT = new ResultParam(0, "操作成功!!!");

    /**
     * 系统异常
     */
    public static final ResultParam SYSTEM_ERROR_RESULT = new ResultParam(1, "系统异常, 请稍后重试!!!");

    /**
     * 登录异常
     */
    public static final ResultParam LOGIN_ERROR_RESULT = new ResultParam(2, "登录信息已失效, 请重新登录!!!");

    /**
     * 请求参数异常
     */
    public static final ResultParam PARAM_ERROR_RESULT = new ResultParam(3, "请求参数异常, 请重试!!!");

    /**
     * 操作失败
     */
    public static final ResultParam FAIL_RESULT = new ResultParam(4, "操作失败, 请重试!!!");

    /**
     * 默认错误编码
     */
    public static final int ERROR = 9;

    /**
     * 成功编码
     */
    public static final int SUCCESS = 0;

    public ResultParam() {
    }

    public ResultParam(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultParam(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultParam(ResultParam param, Object data) {
        this.code = param.getCode();
        this.message = param.getMessage();
        this.data = data;
    }

    public static ResultParam ok() {
        return SUCCESS_RESULT;
    }

    public static ResultParam ok(String message) {
        return new ResultParam(SUCCESS_RESULT.getCode(), message);
    }

    public static ResultParam error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static ResultParam error(String message) {
        return error(500, message);
    }

    public static ResultParam error(int code, String message) {
        return new ResultParam(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
